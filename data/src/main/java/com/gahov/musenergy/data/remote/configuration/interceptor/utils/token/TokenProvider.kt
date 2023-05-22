package com.gahov.musenergy.data.remote.configuration.interceptor.utils.token

import com.gahov.musenergy.data.local.entities.TokenData

interface TokenProvider {

    fun getToken(): String?

    fun setToken(tokenData: TokenData)
}