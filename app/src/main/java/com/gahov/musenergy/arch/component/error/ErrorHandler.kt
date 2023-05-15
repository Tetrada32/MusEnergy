package com.gahov.musenergy.arch.component.error

import com.gahov.domain.entities.failure.Failure

interface ErrorHandler {

    fun parseFailure(failure: Failure)
}