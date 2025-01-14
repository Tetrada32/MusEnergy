package com.gahov.musenergy.feature.stories

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.media3.common.PlaybackException
import androidx.navigation.fragment.findNavController
import com.gahov.domain.entities.stories.StoryEntity
import com.gahov.musenergy.R
import com.gahov.musenergy.arch.router.command.Command
import com.gahov.musenergy.arch.ui.fragment.BaseFragment
import com.gahov.musenergy.common.extensions.hideAnimated
import com.gahov.musenergy.common.extensions.loadImage
import com.gahov.musenergy.common.extensions.showAnimated
import com.gahov.musenergy.databinding.FragmentStoriesBinding
import com.gahov.musenergy.feature.stories.command.StoriesCommand
import com.gahov.musenergy.feature.stories.model.StoryModel
import com.gahov.musenergy.feature.stories.video.player.StoriesPlayerManager
import com.gahov.musenergy.feature.stories.video.player.StoryVideoPlayerController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StoriesFragment : BaseFragment<FragmentStoriesBinding, StoriesViewModel>(
    R.layout.fragment_stories, StoriesViewModel::class.java
), StoryVideoPlayerController {

    override val isBottomNavigationVisible: Boolean = false

    @Inject
    lateinit var playerManager: StoriesPlayerManager

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackAction()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.presenter = viewModel

        loadContent()
        setBackPress()
        initPlayer()
    }

    private fun initPlayer() {
        playerManager.initializePlayer(this@StoriesFragment)
    }

    override fun handleFeatureCommand(command: Command.FeatureCommand) {
        with(command) {
            if (this is StoriesCommand) {
                when (this) {
                    is StoriesCommand.DisplayContent -> displayContent(content)
                    is StoriesCommand.NetworkError -> {}
                    is StoriesCommand.OnPauseStory -> pauseCurrentStory()
                    is StoriesCommand.OnResumeStory -> resumeCurrentStory()
                    is StoriesCommand.OnStoriesFinished -> onBackAction()
                    is StoriesCommand.OnStoryUpdated -> updateStory(currentStoriesList)
                }
            } else {
                super.handleFeatureCommand(command)
            }
        }
    }

    private fun displayContent(storyItems: StoryModel) {
        if (storyItems.itemsCount > 0) {
            setupStories(
                durations = storyItems.durations,
                storiesCount = storyItems.itemsCount,
                stories = storyItems.items
            )
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupStories(
        position: Int = DEFAULT_STORY_POSITION,
        stories: List<StoryEntity>,
        durations: LongArray?,
        storiesCount: Int
    ) {
        val onTouchListener = View.OnTouchListener { _, motionEvent ->
            return@OnTouchListener viewModel.storyPlayAction(motionEvent.action)
        }

        binding.skip.setOnTouchListener(onTouchListener)
        binding.reverse.setOnTouchListener(onTouchListener)
        binding.storiesProgressBars.setStoriesCountDebug(
            storiesCount = storiesCount,
            position = position
        )
        binding.storiesProgressBars.setStoriesListener(viewModel)
        binding.storiesProgressBars.startStories(DEFAULT_STORY_POSITION)

        durations?.let { binding.storiesProgressBars.setStoriesCountWithDurations(it) }

        updateStory(storiesList = stories)
    }

    private fun updateStory(storiesList: List<StoryEntity>) {
        val position = binding.storiesProgressBars.getCurrentPosition()
        try {
            startStories(position)
            display(storiesList[position])
        } catch (e: Exception) {
            e.printStackTrace()
            restartAnimation()
        }
    }

    private fun display(story: StoryEntity) {
        displayUpdatedContent(story)
    }

    private fun displayUpdatedContent(story: StoryEntity) {
        if (story.isVideo()) {
            showPlayerView()
            displayVideo(story)
        } else {
            hidePlayerView()
            loadImage(story.imageUrl)
        }
    }

    override fun onPause() {
        super.onPause()
        binding.storiesProgressBars.abandon()
    }

    private fun loadContent() {
        viewModel.loadContent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.storiesProgressBars.destroy()
        releasePlayer()
    }

    private fun displayVideo(currentItem: StoryEntity) {
        if (currentItem.videoUrl.isNotEmpty()) {
            playerManager.setMediaSource(
                id = currentItem.id,
                videoUrl = currentItem.videoUrl,
                playerView = binding.storiesVideoContainer
            )
        } else {
            hidePlayerView()
        }
    }

    override fun onPlayerError(exception: PlaybackException) {
        binding.storiesProgressBars.skip()
    }

    private fun releasePlayer() {
        playerManager.releasePlayer()
    }

    private fun hidePlayerView() {
        with(binding) {
            storiesVideoContainer.hideAnimated(DEFAULT_STORY_TRANSITION_DURATION)
            storiesImageContainer.showAnimated(DEFAULT_STORY_TRANSITION_DURATION)
        }
    }

    private fun showPlayerView() {
        with(binding) {
            storiesVideoContainer.showAnimated(DEFAULT_STORY_TRANSITION_DURATION)
            storiesImageContainer.hideAnimated(DEFAULT_STORY_TRANSITION_DURATION)
        }
    }

    override fun onPlaybackFinished() {}

    override fun onRenderedFirstFrame(currentVideoId: String) {}

    private fun pauseCurrentStory() {
        playerManager.pause()
        binding.storiesProgressBars.pause()
    }

    private fun resumeCurrentStory() {
        playerManager.play()
        binding.storiesProgressBars.resume()
    }

    private fun loadImage(image: String) {
        binding.storiesImageContainer.loadImage(image, crossfade = false)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.storiesProgressBars.destroy()
    }

    private fun setBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            backPressedCallback
        )
    }

    private fun startStories(position: Int = DEFAULT_STORY_POSITION) {
        binding.storiesProgressBars.startStories(from = position)
    }

    private fun restartAnimation() {
        loadContent()
    }

    fun onBackAction() {
        releasePlayer()
        findNavController().popBackStack()
    }

    companion object {
        const val DEFAULT_STORY_TRANSITION_DURATION = 100L
        const val DEFAULT_STORY_POSITION = 0
    }
}
