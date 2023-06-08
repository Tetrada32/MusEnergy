package com.gahov.musenergy.data.remote.configuration.interceptor

import com.gahov.musenergy.data.common.util.API_TOKEN
import com.gahov.musenergy.data.local.entities.TokenData
import com.gahov.musenergy.data.remote.configuration.interceptor.utils.token.TokenProvider
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val tokenProvider: TokenProvider) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var token = tokenProvider.getToken()

        if (token.isNullOrBlank()) {
            token = API_TOKEN
            tokenProvider.setToken(TokenData(accessToken = token))
        }

        val request = chain.request()
            .newBuilder()
            .header(
                AUTHORIZATION_HEADER_KEY,
                "$AUTHORIZATION_HEADER_VALUE $token"
            )
            .build()
        return chain.proceed(request)
    }

    companion object {
        const val AUTHORIZATION_HEADER_KEY = "Authorization"
        const val AUTHORIZATION_HEADER_VALUE = "Bearer"
    }
}
