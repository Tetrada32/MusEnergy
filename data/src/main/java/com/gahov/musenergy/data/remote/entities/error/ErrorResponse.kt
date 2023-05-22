package com.gahov.musenergy.data.remote.entities.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("status")
    val status: String? = null,

    @SerialName("code")
    val code: String? = null,

    @SerialName("message")
    val message: String? = null,

    )