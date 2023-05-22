package com.gahov.musenergy.data.local.source.auth.impl

import com.gahov.musenergy.data.local.entities.TokenData
import com.gahov.musenergy.data.local.source.auth.TokenSource
import com.gahov.musenergy.data.local.storage.authorization.AuthorizationLocalStorage

class ImplTokenSource(
    private val storage: AuthorizationLocalStorage,
) : TokenSource {

    override fun getToken(): TokenData {
        return TokenData(
            accessToken = storage.accessToken
        )
    }

    override fun updateToken(tokenData: TokenData) {
        storage.accessToken = tokenData.accessToken
    }

    override suspend fun clearToken() {
        storage.apply {
            accessToken = ""
        }
    }
}