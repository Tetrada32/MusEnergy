package com.gahov.musenergy.arch.di.module

import android.content.SharedPreferences
import com.gahov.domain.component.device.DeviceInfo
import com.gahov.domain.component.device.UserAgent
import com.gahov.domain.component.device.UserAgentProvider
import com.gahov.musenergy.BuildConfig
import com.gahov.musenergy.arch.component.device.AndroidDeviceInfo
import com.gahov.musenergy.data.local.source.auth.impl.ImplTokenSource
import com.gahov.musenergy.data.local.storage.authorization.ImplAuthorizationLocalStorage
import com.gahov.musenergy.data.remote.configuration.NetworkConfiguration
import com.gahov.musenergy.data.remote.configuration.NetworkFactory
import com.gahov.musenergy.data.remote.configuration.interceptor.provider.DefaultInterceptorProvider
import com.gahov.musenergy.data.remote.configuration.interceptor.utils.token.BearerProvider
import com.gahov.musenergy.data.remote.configuration.interceptor.utils.token.TokenProvider
import com.gahov.musenergy.data.remote.configuration.url.BaseUrlProvider
import com.gahov.musenergy.data.remote.configuration.url.UrlProvider
import com.gahov.musenergy.data.remote.protocol.MainProtocol
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideNetworkFactory(
        configuration: NetworkConfiguration.AuthConfiguration,
        interceptor: DefaultInterceptorProvider
    ): NetworkFactory {
        return NetworkFactory(
            configuration = configuration,
            interceptor = interceptor
        )
    }

    @Provides
    @Singleton
    internal fun provideDefaultInterceptor(
        configuration: NetworkConfiguration.AuthConfiguration,
        userAgent: UserAgentProvider,
        logger: com.gahov.domain.component.logger.Logger,
    ) = DefaultInterceptorProvider(
        configuration = configuration,
        userAgentProvider = userAgent,
        logger = logger
    )

    @Provides
    @Singleton
    internal fun provideDefaultAuthConfiguration(
        tokenProvider: TokenProvider,
        urlProvider: UrlProvider,
    ) = NetworkConfiguration.AuthConfiguration(
        tokenProvider = tokenProvider,
        serverUrlProvider = urlProvider,
    )

    @Provides
    @Singleton
    internal fun provideTokenProvider(
        sharedPreferences: SharedPreferences
    ): TokenProvider = BearerProvider(
        tokenSource = ImplTokenSource(
            storage =
            ImplAuthorizationLocalStorage(preferences = sharedPreferences)
        )
    )

    @Provides
    @Singleton
    internal fun provideServerUrlProvider(): UrlProvider = BaseUrlProvider()

    @Provides
    @Singleton
    internal fun provideUserAgent(
        deviceInfo: DeviceInfo
    ): UserAgentProvider = UserAgent(deviceInfo = deviceInfo)

    @Provides
    @Singleton
    internal fun provideDeviceInfo(): DeviceInfo = object : AndroidDeviceInfo() {
        override val versionCode: Int = BuildConfig.VERSION_CODE
        override val versionName: String = BuildConfig.VERSION_NAME
    }

    @Provides
    @Reusable
    internal fun provideNewsProtocol(
        configuration: NetworkConfiguration.AuthConfiguration,
        interceptor: DefaultInterceptorProvider
    ): MainProtocol {
        return NetworkFactory.createService(
            protocol = MainProtocol::class.java,
            configuration = configuration,
            interceptors = interceptor
        )
    }
}