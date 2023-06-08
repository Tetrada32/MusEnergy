package com.gahov.musenergy.feature.articles.model

import com.gahov.musenergy.arch.ui.view.model.IconProvider
import com.gahov.musenergy.arch.ui.view.model.TextProvider
import com.gahov.musenergy.feature.articles.ArticleViewType
import java.io.Serializable

sealed class ArticleModel(private val id: String) : Serializable {

    data class BaseArticleData(
        val image: IconProvider,
        val title: TextProvider,
        val description: TextProvider,
        val author: TextProvider,
        val urlToSource: TextProvider,
        val publishedAt: TextProvider,
        val content: TextProvider,
        val sourceId: TextProvider,
        val sourceName: TextProvider
    )

    data class DefaultArticle(
        val id: String = DEFAULT_ARTICLE_ID,
        val articleData: BaseArticleData
    ) : ArticleModel(id = id), Serializable {

        override fun getModelType(): ArticleViewType {
            return ArticleViewType.DEFAULT_ARTICLE
        }
    }

    data class InitialArticle(
        val id: String = INITIAL_ARTICLE_ID,
        val articleData: BaseArticleData
    ) : ArticleModel(id = id), Serializable {

        override fun getModelType(): ArticleViewType {
            return ArticleViewType.INITIAL_ARTICLE
        }
    }

    data class CategoryArticle(
        val id: String = CATEGORY_ARTICLE_ID,
        val articleData: BaseArticleData,
        val categoryName: TextProvider,
    ) : ArticleModel(id = id), Serializable {

        override fun getModelType(): ArticleViewType {
            return ArticleViewType.CATEGORY_ARTICLE
        }
    }

    fun areItemsSame(model: ArticleModel): Boolean {
        return model.id == id
    }

    abstract fun getModelType(): ArticleViewType

    companion object {
        const val DEFAULT_ARTICLE_ID = "default_article_id"
        const val INITIAL_ARTICLE_ID = "initial_article_id"
        const val CATEGORY_ARTICLE_ID = "category_article_id"
    }
}