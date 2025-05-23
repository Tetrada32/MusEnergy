package com.gahov.musenergy.feature.search

import androidx.lifecycle.LiveData
import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.articles.CategoryEntity
import com.gahov.domain.entities.search.SearchNewsCategory
import com.gahov.domain.usecase.articles.categories.LoadSearchCategoriesUseCase
import com.gahov.musenergy.arch.controller.BaseViewModel
import com.gahov.musenergy.arch.lifecycle.SingleLiveEvent
import com.gahov.musenergy.feature.search.adapter.factory.SearchEntityToModelBuilder
import com.gahov.musenergy.feature.search.adapter.model.SearchSectionModel
import com.gahov.musenergy.feature.search.presenter.SearchPresenter
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val loadSearchCategoriesUseCase: LoadSearchCategoriesUseCase
) : BaseViewModel(), SearchPresenter {

    private val modelBuilder = SearchEntityToModelBuilder()

    private val _searchData = SingleLiveEvent<List<SearchSectionModel>>()
    val searchData: LiveData<List<SearchSectionModel>>
        get() = _searchData

    fun loadContent() {
        launch { loadSearchCategories() }
    }

    private suspend fun loadSearchCategories() {
        when (val result = loadSearchCategoriesUseCase.execute(param = null)) {
            is Either.Left -> {}
            is Either.Right -> {
                _searchData.postValue(createDisplayModel(result.success))
            }
        }
    }

    private fun createDisplayModel(searchData: List<CategoryEntity>): List<SearchSectionModel> {
        return modelBuilder.create(searchData)
    }

    private fun navigateToArticleList(category: SearchNewsCategory) {
        navigateDirection(SearchFragmentDirections.actionSearchToArticleList(category))
    }

    override fun onSearchMainSectionPressed(model: SearchSectionModel.SearchMainSection) {
        navigateToArticleList(SearchNewsCategory.CATEGORY_ALL)
    }

    override fun onSearchCategoryPressed(model: SearchSectionModel.SearchCategorySection) {
        navigateToArticleList(model.type)
    }
}