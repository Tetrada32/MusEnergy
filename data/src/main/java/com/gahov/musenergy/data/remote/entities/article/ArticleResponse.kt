package com.gahov.musenergy.data.remote.entities.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(

    @SerialName("source")
    val sourceResponse: SourceResponse? = null,

    @SerialName("author")
    val author: String? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("url")
    val url: String? = null,

    @SerialName("urlToImage")
    val urlToImage: String? = null,

    @SerialName("publishedAt")
    val publishedAt: String? = null,

    @SerialName("content")
    val content: String? = null,

    )