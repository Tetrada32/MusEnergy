package com.gahov.domain.entities.failure

sealed class ServerError : Failure.FeatureFailure() {
    object ServerCommon : ServerError()
}