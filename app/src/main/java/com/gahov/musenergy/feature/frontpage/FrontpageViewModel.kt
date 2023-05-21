package com.gahov.musenergy.feature.frontpage

import com.gahov.musenergy.arch.controller.BaseViewModel
import javax.inject.Inject

class FrontpageViewModel @Inject constructor() : BaseViewModel() {

    init {
        setLoadingStatus(isLoading = true)
    }

    private fun setLoadingStatus(isLoading: Boolean) {
        setLoading(isLoading)
    }

    fun dismissLoading() {
        setLoadingStatus(isLoading = false)
    }
}