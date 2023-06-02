package com.gahov.domain.entities.search

enum class SearchNewsCategory(
    val id: String,
) : java.io.Serializable {
    CATEGORY_ALL("music"),
    CATEGORY_ROCK("Rock"),
    CATEGORY_CLASSIC("Classic"),
    CATEGORY_POP("Pop"),
    CATEGORY_RAP("Rap"),
    CATEGORY_OST("Ost"),
    CATEGORY_JAZZ("Jazz"),
    CATEGORY_BLUES("Blues"),
    CATEGORY_DEFAULT("");

    companion object {
        fun from(id: String): SearchNewsCategory =
            SearchNewsCategory.values().find { it.id == id } ?: CATEGORY_DEFAULT
    }
}