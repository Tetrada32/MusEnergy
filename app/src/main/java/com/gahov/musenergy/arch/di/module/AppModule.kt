package com.gahov.musenergy.arch.di.module

import android.app.Application
import android.content.Context
import com.gahov.domain.component.logger.Logger
import com.gahov.musenergy.arch.component.coil.CoilImagePreloader
import com.gahov.musenergy.arch.component.coil.impl.CoilImagePreloaderImpl
import com.gahov.musenergy.arch.component.error.DefaultFailureHandler
import com.gahov.musenergy.arch.component.error.ErrorHandler
import com.gahov.musenergy.arch.component.logger.AndroidLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module(
    includes = [
        ViewModelModule::class
    ]
)
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    internal fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideCoilImagePreloader(context: Context): CoilImagePreloader {
        return CoilImagePreloaderImpl(context = context)
    }

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