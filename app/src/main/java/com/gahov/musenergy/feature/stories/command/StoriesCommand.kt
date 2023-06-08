package com.gahov.musenergy.feature.stories.command

import com.gahov.domain.entities.stories.StoryEntity
import com.gahov.musenergy.arch.router.command.Command
import com.gahov.musenergy.feature.stories.model.StoryModel


sealed class StoriesCommand : Command.FeatureCommand() {

    data class DisplayContent(val content: StoryModel) : StoriesCommand()

    object OnResumeStory : StoriesCommand()

    object OnPauseStory : StoriesCommand()

    object OnStoriesFinished : StoriesCommand()

    data class NetworkError(val errorMessage: String) : StoriesCommand()

    data class OnStoryUpdated(val currentStoriesList: List<StoryEntity>) : StoriesCommand()
}