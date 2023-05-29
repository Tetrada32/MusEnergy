package com.gahov.musenergy.feature.articles.model

import com.gahov.musenergy.arch.ui.view.model.IconProvider
import com.gahov.musenergy.arch.ui.view.model.TextProvider
import com.gahov.musenergy.feature.articles.ArticleViewType
import java.io.Serializable

sealed class ArticleModel(private val id: String): Serializable {

    data class DefaultArticle(
        val id: String = DEFAULT_ARTICLE_ID,
        val image: IconProvider,
        val title: TextProvider,
        val description: TextProvider,
        val author: TextProvider,
        val urlToSource: TextProvider,
        val publishedAt: TextProvider,
        val content: TextProvider,
        val sourceId: TextProvider,
        val sourceName: TextProvider
    ) : ArticleModel(id = id), Serializable {

        override fun getModelType(): ArticleViewType {
            return ArticleViewType.DEFAULT_ARTICLE
        }
    }

    fun areItemsSame(model: ArticleModel): Boolean {
        return model.id == id
    }

    abstract fun getModelType(): ArticleViewType

    companion object {
        const val DEFAULT_ARTICLE_ID = "default_article_id"
    }
}