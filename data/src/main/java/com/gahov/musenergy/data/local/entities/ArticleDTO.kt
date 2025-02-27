package com.gahov.musenergy.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * An entity representing separate article data, stored in a database.
 *
 */


@Entity(
    tableName = "articles"
)
data class ArticleDTO(
    var id: Long? = 0,
    var title: String? = "",
    var image: String? = "",
    var description: String? = "",
    var author: String? = "",
    var urlToSource: String? = "",
    var publishedAt: String? = "",
    var content: String? = "",
    var sourceId: String? = "",
    var sourceName: String? = "",
    var isFavorite: Boolean = false
) {

    /**
     * The primary key auto-generated by the database.
     * That means that the DTO is not unique.
     */

    @PrimaryKey(autoGenerate = true)
    var uid: Long = 0
}