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
                videoUrl = "https://assets.mixkit.co/videos/preview/mixkit-young-mixed-rock-band-5125-large.mp4",
                videoDuration = 15000L,
            ),
            StoryEntity(
                id = "2",
                imageUrl = "",
                videoUrl = "https://assets.mixkit.co/videos/preview/mixkit-guitarist-girl-seen-from-behind-while-playing-a-song-5122-large.mp4",
                videoDuration = 3800L,
            ),
            StoryEntity(
                id = "4",
                imageUrl = "",
                videoUrl = "https://assets.mixkit.co/videos/preview/mixkit-person-playing-electric-guitar-close-up-3594-large.mp4",
                videoDuration = 5000L,
            )
        )
    }
}