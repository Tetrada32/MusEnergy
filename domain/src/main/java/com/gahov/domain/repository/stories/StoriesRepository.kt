package com.gahov.domain.repository.stories

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.stories.StoryEntity


interface StoriesRepository {

    suspend fun getStoriesList(): Either<Failure, List<StoryEntity>>

}