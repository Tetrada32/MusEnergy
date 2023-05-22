package com.gahov.musenergy.arch.di.module

import com.gahov.domain.component.logger.Logger
import com.gahov.musenergy.arch.component.error.DefaultFailureHandler
import com.gahov.musenergy.arch.component.error.ErrorHandler
import com.gahov.musenergy.arch.component.logger.AndroidLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LoggerModule {

    @Provides
    @Singleton
    internal fun provideLogger(): Logger {
        return AndroidLogger()
    }

    @Provides
    @Singleton
    internal fun provideErrorHandler(logger: Logger): ErrorHandler {
        return DefaultFailureHandler(logger = logger)
    }
}