package com.gahov.musenergy.data.remote.entities.success

import com.gahov.musenergy.data.remote.entities.article.ArticleResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DefaultSuccessResponse(
    @SerialName("status")
    val status: String? = null,

    @SerialName("totalResults")
    val totalResults: Int? = null,

    @SerialName("articles")
    val articleResponses: List<ArticleResponse>? = null,
)