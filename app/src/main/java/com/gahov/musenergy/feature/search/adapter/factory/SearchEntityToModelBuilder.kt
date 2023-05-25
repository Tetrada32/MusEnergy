package com.gahov.musenergy.feature.search.adapter.factory

import com.gahov.domain.entities.news.CategoryEntity
import com.gahov.domain.entities.search.SearchNewsCategory
import com.gahov.musenergy.arch.ui.view.model.IconProvider
import com.gahov.musenergy.arch.ui.view.model.TextProvider
import com.gahov.musenergy.feature.search.adapter.model.SearchSectionModel


class SearchEntityToModelBuilder {

    private val searchItems: MutableList<SearchSectionModel> = mutableListOf()

    fun create(searchData: List<CategoryEntity>): List<SearchSectionModel> {
        searchItems.clear()
        searchData.map { data ->
            createMainSection(searchData = data)
            createCategorySection(searchData = data)
        }
        return searchItems
    }

    private fun createMainSection(searchData: CategoryEntity) {
        with(searchData) {
            if (id == DEFAULT_MAIN_SECTION_ID) {
                searchItems.add(
                    SearchSectionModel.SearchMainSection(
                        id = "0",
                        sectionImageId = IconProvider.Url(imageUrl.toString()),
                        sectionText = TextProvider.Text(title.toString()),
                    )
                )
            }
        }
    }

    private fun createCategorySection(searchData: CategoryEntity) {
        with(searchData) {
            if (id != DEFAULT_MAIN_SECTION_ID) {
                searchItems.add(
                    SearchSectionModel.SearchCategorySection(
                        id = id.toString(),
                        sectionImageId = IconProvider.Url(imageUrl.toString()),
                        sectionText = TextProvider.Text(title.toString()),
                        type = SearchNewsCategory.from(title.toString())
                    )
                )
            }
        }
    }

    companion object {
        private val DEFAULT_MAIN_SECTION_ID = SearchNewsCategory.CATEGORY_ALL.id
    }
}