package com.gahov.musenergy.arch.controller

import com.gahov.musenergy.arch.router.command.Command
import com.gahov.musenergy.arch.ui.view.model.TextProvider
import com.gahov.domain.entities.failure.Failure

interface Controller {
    fun showMessage(message: TextProvider)

    fun setLoading(boolean: Boolean)

    fun navigate(command: Command)

    fun handleFailure(failure: Failure)

}