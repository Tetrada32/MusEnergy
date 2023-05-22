package com.gahov.musenergy.data.source.news

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.musenergy.data.remote.entities.success.DefaultSuccessResponse

interface NewsRemoteSource {

    suspend fun loadEverything(): Either<Failure, DefaultSuccessResponse>
}