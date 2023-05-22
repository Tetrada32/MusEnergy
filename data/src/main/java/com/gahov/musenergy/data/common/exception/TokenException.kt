package com.gahov.musenergy.data.common.exception

class TokenException(override val message: String = INVALID) : Throwable(message = message) {

    companion object {
        private const val INVALID = "Invalid token"
    }
}