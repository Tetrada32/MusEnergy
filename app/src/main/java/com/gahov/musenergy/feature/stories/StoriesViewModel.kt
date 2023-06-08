package com.gahov.musenergy.feature.stories

import android.view.MotionEvent
import com.gahov.domain.component.logger.Logger
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.stories.StoryEntity
import com.gahov.domain.usecase.stories.LoadStoriesListUseCase
import com.gahov.musenergy.arch.controller.BaseViewModel
import com.gahov.musenergy.feature.stories.command.StoriesCommand
import com.gahov.musenergy.feature.stories.model.StoryModel
import com.gahov.musenergy.feature.stories.presenter.StoriesPresenter
import com.gahov.musenergy.feature.stories.widget.storyviewer.StoriesProgressView
import javax.inject.Inject

class StoriesViewModel @Inject constructor(
    private val loadStoriesListUseCase: LoadStoriesListUseCase,
    private val logger: Logger
) : BaseViewModel(), StoriesPresenter, StoriesProgressView.StoriesListener {

    var pressTime = 0L

    private var pressLimit = 500L

    var currentStoriesList: List<StoryEntity>? = null

    fun loadContent() {
        currentStoriesList = null

        launch {
            when (val result = loadStoriesListUseCase.execute()) {
                is Either.Right -> onResultSuccess(result.success)
                is Either.Left -> onResultFailure(result.failure)
            }
        }
    }

    private fun onResultSuccess(success: List<StoryEntity>) {
        currentStoriesList = success

        handleCommand(
            StoriesCommand.DisplayContent(
                StoryModel(
                    items = success,
                    durations = setupStoriesDurations(success),
                    itemsCount = success.size,
                )
            )
        )
    }

    private fun onResultFailure(failure: Failure) {
        logger.log(message = "Failure: \n $failure")
    }

    private fun setupStoriesDurations(list: List<StoryEntity>): LongArray? {
        val storiesDurations = LongArray(list.size)
        list.mapIndexed { index, story ->
            storiesDurations[index] = story.videoDuration ?: return null
        }
        return if (storiesDurations.size == list.size) {
            storiesDurations
        } else {
            null
        }
    }

    fun storyPlayAction(action: Int): Boolean {
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                return pauseStory()
            }
            MotionEvent.ACTION_UP -> {
                return resumeStory()
            }
        }
        return false
    }

    override fun pauseStory(): Boolean {
        handleCommand(StoriesCommand.OnPauseStory)
        pressTime = System.currentTimeMillis()
        return false
    }

    override fun resumeStory(): Boolean {
        handleCommand(StoriesCommand.OnResumeStory)
        val now = System.currentTimeMillis()
        return pressLimit < now - pressTime
    }

    override fun onNext() {
        updateStory()
    }

    override fun onPrev() {
        updateStory()
    }

    private fun updateStory() {
        handleCommand(StoriesCommand.OnStoryUpdated(currentStoriesList.orEmpty()))
    }

    override fun onComplete() {
        handleCommand(StoriesCommand.OnStoriesFinished)
    }
}