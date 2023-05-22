package com.gahov.musenergy.arch.di.module

import android.app.Application
import android.content.Context
import com.gahov.musenergy.arch.component.coil.CoilImagePreloader
import com.gahov.musenergy.arch.component.coil.impl.CoilImagePreloaderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module(
    includes = [
        LoggerModule::class,
        MapperModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        SharedPreferencesModule::class,
        SourceModule::class,
        ViewModelModule::class,
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
}