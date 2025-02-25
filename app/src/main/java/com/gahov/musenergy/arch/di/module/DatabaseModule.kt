package com.gahov.musenergy.arch.di.module

import android.content.Context
import androidx.room.Room
import com.gahov.musenergy.data.local.db.AppDatabase
import com.gahov.musenergy.data.local.db.AppDatabase.Companion.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideArticlesDao(db: AppDatabase) = db.articlesDao()
}