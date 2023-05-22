package com.gahov.musenergy.data.remote.configuration.interceptor.provider

import com.gahov.domain.component.device.UserAgentProvider
import com.gahov.domain.component.logger.Logger
import com.gahov.musenergy.data.remote.configuration.NetworkConfiguration
import com.gahov.musenergy.data.remote.configuration.interceptor.TokenInterceptor
import com.gahov.musenergy.data.remote.configuration.interceptor.UserAgentInterceptor
import com.gahov.musenergy.data.remote.configuration.interceptor.utils.InterceptorLogger
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

class DefaultInterceptorProvider(
    configuration: NetworkConfiguration,
    userAgentProvider: UserAgentProvider,
    logger: Logger
) : InterceptorProvider {

    override val networkInterceptors: ArrayList<Interceptor> = arrayListOf()
    override val interceptors: ArrayList<Interceptor> = arrayListOf()

    private val userAgentInterceptor: Interceptor by lazy { UserAgentInterceptor(userAgentProvider) }

    private val loggingInterceptor: Interceptor by lazy {
        HttpLoggingInterceptor(InterceptorLogger(logger))
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
    }

    init {
        networkInterceptors.add(userAgentInterceptor)

        if (configuration.withLogs) {
            networkInterceptors.add(loggingInterceptor)
        }

        if (configuration.withAuth && configuration is NetworkConfiguration.AuthConfiguration) {
            interceptors.add(TokenInterceptor(configuration.tokenProvider))
        }
    }
}