package com.gahov.domain.usecase.stories

import com.gahov.domain.common.usecase.AsyncUseCase
import com.gahov.domain.common.usecase.UseCase
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.stories.StoryEntity
import com.gahov.domain.repository.stories.StoriesRepository

class LoadStoriesListUseCase(
    private val repository: StoriesRepository
) : AsyncUseCase<List<StoryEntity>>() {

    override suspend fun execute(param: UseCase.Params?): Either<Failure, List<StoryEntity>> {
        return repository.getStoriesList()
    }
}