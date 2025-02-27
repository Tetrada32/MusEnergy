package com.gahov.musenergy.data.local.storage.articles

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.gahov.musenergy.data.local.entities.ArticleDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDao {

    @Upsert
    fun insertItems(articlesList: List<ArticleDTO>)

    @Query("SELECT * FROM articles")
    fun fetchAll(): List<ArticleDTO>

    @Query("SELECT * FROM articles WHERE id = :id")
    fun getItemById(id: Long): Flow<ArticleDTO>

    @Query("UPDATE articles SET isFavorite = :isFavorite WHERE id = :id")
    fun updateFavoriteStatus(id: Long, isFavorite: Boolean)

    @Query("SELECT * FROM articles WHERE isFavorite = 1")
    fun fetchFavorites(): Flow<List<ArticleDTO>>

    @Query("DELETE FROM articles WHERE id = :id AND isFavorite = 1")
    fun deleteFavoriteArticle(id: Long)

    @Query("DELETE FROM articles WHERE isFavorite = 1")
    fun deleteFavorites()

    @Query("DELETE FROM articles")
    fun deleteAll()

}