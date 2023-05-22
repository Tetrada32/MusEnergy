package com.gahov.musenergy.data.remote.entities.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceResponse(
    @SerialName("id")
    val id: String? = "",

    @SerialName("name")
    val name: String = ""
)