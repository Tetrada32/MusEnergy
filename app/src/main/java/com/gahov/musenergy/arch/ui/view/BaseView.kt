package com.gahov.musenergy.arch.ui.view

import com.gahov.musenergy.arch.ui.view.model.TextProvider
import com.gahov.domain.entities.failure.Failure

interface BaseView {
    fun displayError(failure: Failure)

    fun showMessage(textProvider: TextProvider)
}