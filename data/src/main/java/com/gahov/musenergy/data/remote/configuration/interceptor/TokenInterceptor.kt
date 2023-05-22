package com.gahov.musenergy.data.remote.configuration.interceptor

import com.gahov.musenergy.data.remote.configuration.interceptor.utils.token.TokenProvider
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val tokenProvider: TokenProvider) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenProvider.getToken()

        if (token.isNullOrBlank()) {
            return chain.proceed(chain.request())
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
