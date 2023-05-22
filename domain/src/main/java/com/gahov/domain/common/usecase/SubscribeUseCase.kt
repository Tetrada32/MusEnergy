package com.gahov.domain.common.usecase

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import kotlinx.coroutines.flow.Flow

abstract class SubscribeUseCase<out Result : Any> : UseCase<Result> {

    abstract suspend fun execute(param: UseCase.Params? = null): Flow<Either<Failure, Result>>

    suspend operator fun invoke(
        param: UseCase.Params? = null,
        onResult: (Flow<Either<Failure, Result>>) -> Unit = {},
    ) {
        onResult(execute(param))
    }
}