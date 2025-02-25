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

    /**
     * The content is hardcoded as I don't have functionally for adding stories fow now :D
     */
    private fun getHardcodedStoriesList(): List<StoryEntity> {
        return listOf(
            StoryEntity(
                id = "0",
                imageUrl = "",
                videoUrl = "https://videos.pexels.com/video-files/3189294/3189294-sd_360_640_25fps.mp4",
                videoDuration = 12000L,
            ),
            StoryEntity(
                id = "1",
                imageUrl = "",
                videoUrl = "https://videos.pexels.com/video-files/2795746/2795746-sd_360_640_25fps.mp4",
                videoDuration = 15000L,
            ),
            StoryEntity(
                id = "2",
                imageUrl = "",
                videoUrl = "https://videos.pexels.com/video-files/3713449/3713449-sd_506_960_25fps.mp4",
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