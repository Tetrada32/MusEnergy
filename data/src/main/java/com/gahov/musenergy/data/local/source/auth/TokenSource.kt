package com.gahov.musenergy.data.local.source.auth

import com.gahov.musenergy.data.local.entities.TokenData
import com.gahov.domain.source.Source

interface TokenSource : Source {

    fun getToken(): TokenData

    fun updateToken(tokenData: TokenData)

    suspend fun clearToken()

}