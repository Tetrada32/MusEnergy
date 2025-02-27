package com.gahov.domain.entities.articles

data class ArticleEntity(
    val id: Long? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null,
    val sourceId: String? = null,
    val sourceName: String? = null,
    var isFavorite: Boolean = false
) {
    companion object {
        const val PLACEHOLDER_IMAGE = "https://www.roadiemusic.com/blog/wp-content/uploads/2020/02/Is-Rock-Music-Dead.png"
    }
}