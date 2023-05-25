package com.gahov.musenergy.feature.search.presenter

import com.gahov.musenergy.feature.search.adapter.model.SearchSectionModel

interface SearchPresenter {

    fun onSearchMainSectionPressed(model: SearchSectionModel.SearchMainSection)

    fun onSearchCategoryPressed(model: SearchSectionModel.SearchCategorySection)

}