package com.gahov.musenergy.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gahov.musenergy.data.local.db.AppDatabase.Companion.DB_VERSION
import com.gahov.musenergy.data.local.entities.ArticleDTO
import com.gahov.musenergy.data.local.storage.articles.ArticlesDao

/**
 * An abstract Room database class representing the application's database.
 *
 * @see Database
 */
@Database(
    entities = [ArticleDTO::class],
    version = DB_VERSION
)

abstract class AppDatabase : RoomDatabase() {

    /**
     * Provides access to the ArticlesDao for database operations.
     *
     * @return An instance of the ArticlesDao interface.
     */
    abstract fun articlesDao(): ArticlesDao

    /**
     * A companion object containing constants and configuration for the database.
     */
    companion object {

        /**
         * The version of the database.
         * Should be increased every time the developer changes DTO or DB config.
         */
        const val DB_VERSION = 1

        /**
         * The name of the database file.
         */
        var DB_NAME = "gahov_musenergy.db"
    }
}