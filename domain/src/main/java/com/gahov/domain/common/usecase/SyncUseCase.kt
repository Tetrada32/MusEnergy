package com.gahov.domain.common.usecase

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure

abstract class SyncUseCase<out Type : Any> : UseCase<Type> {

    abstract fun execute(param: UseCase.Params? = null): Either<Failure, Type>

    operator fun invoke(
        param: UseCase.Params? = null,
        onResult: (Either<Failure, Type>) -> Unit = {},
    ) {
        onResult(execute(param))
    }
}