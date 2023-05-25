package com.gahov.musenergy.feature.search.adapter.model

import com.gahov.domain.entities.search.SearchNewsCategory
import com.gahov.musenergy.arch.ui.view.model.IconProvider
import com.gahov.musenergy.arch.ui.view.model.TextProvider
import com.gahov.musenergy.feature.search.adapter.SearchListViewType


sealed class SearchSectionModel(private val id: String) {

    data class SearchMainSection(
        val id: String = SEARCH_MAIN_ID,
        val sectionImageId: IconProvider,
        val sectionText: TextProvider
    ) : SearchSectionModel(id = id) {
        override fun getModelType(): SearchListViewType =
            SearchListViewType.SEARCH_MAIN_CATEGORY_SECTION
    }

    data class SearchCategorySection(
        val id: String,
        val type: SearchNewsCategory,
        val sectionImageId: IconProvider,
        val sectionText: TextProvider
    ) : SearchSectionModel(id = id) {
        override fun getModelType(): SearchListViewType = SearchListViewType.SEARCH_CATEGORY_SECTION
    }

    fun areItemsSame(model: SearchSectionModel): Boolean {
        return model.id == id
    }

    abstract fun getModelType(): SearchListViewType

    companion object {
        private const val SEARCH_MAIN_ID = "search_main_section"
    }
}