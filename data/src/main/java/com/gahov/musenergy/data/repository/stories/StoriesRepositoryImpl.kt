package com.gahov.musenergy.data.repository.stories

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.common.right
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.stories.StoryEntity
import com.gahov.domain.repository.stories.StoriesRepository

class StoriesRepositoryImpl : StoriesRepository {

    override suspend fun getStoriesList(): Either<Failure, List<StoryEntity>> {
        return right(getHardcodedStoriesList())
    }

    private fun getHardcodedStoriesList(): List<StoryEntity> {
        return listOf(
            StoryEntity(
                id = "0",
                imageUrl = "",
                videoUrl = "https://assets.mixkit.co/videos/preview/mixkit-young-woman-playing-a-white-electric-guitar-5123-large.mp4",
                videoDuration = 12000L,
            ),
            StoryEntity(
                id = "1",
                imageUrl = "",
                videoUrl = "https://assets.mixkit.co/videos/51930/51930-720.mp4",
                videoDuration = 15000L,
            ),
            StoryEntity(
                id = "2",
                imageUrl = "",
                videoUrl = "https://assets.mixkit.co/videos/42812/42812-720.mp4",
                videoDuration = 3800L,
            ),
            StoryEntity(
                id = "4",
                imageUrl = "",
                videoUrl = "https://assets.mixkit.co/videos/51931/51931-720.mp4",
                videoDuration = 5000L,
            )
        )
    }
}