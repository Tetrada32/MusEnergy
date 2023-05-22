package com.gahov.musenergy.data.source.news

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.musenergy.data.remote.call
import com.gahov.musenergy.data.remote.entities.success.DefaultSuccessResponse
import com.gahov.musenergy.data.remote.protocol.MainProtocol

class NewsRemoteSourceImpl(
    private val protocol: MainProtocol,
) : NewsRemoteSource {

    override suspend fun loadEverything(): Either<Failure, DefaultSuccessResponse> {
        return call { protocol.getEverything() }
    }
}