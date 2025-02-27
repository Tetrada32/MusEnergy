package com.gahov.musenergy.feature.articles.factory


import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.domain.entities.search.SearchNewsCategory
import com.gahov.musenergy.arch.ui.view.model.IconProvider
import com.gahov.musenergy.arch.ui.view.model.TextProvider
import com.gahov.musenergy.feature.articles.model.ArticleModel
import com.gahov.musenergy.feature.articles.model.ArticleModel.Companion.CATEGORY_ARTICLE_ID
import com.gahov.musenergy.feature.articles.model.ArticleModel.Companion.DEFAULT_ARTICLE_ID
import com.gahov.musenergy.feature.articles.model.ArticleModel.Companion.INITIAL_ARTICLE_ID
import com.gahov.musenergy.feature.articles.model.BaseArticleData

class ArticleEntityToModelBuilder : ArticleEntityBuilder {

    private val articleItems: MutableList<ArticleModel> = mutableListOf()

    override fun buildFrontpageList(articleEntityItems: List<ArticleEntity>): List<ArticleModel> {
        articleItems.clear()
        articleEntityItems.mapIndexed { index, data ->
            if (index == FIRST_INDEX) {
                createInitialArticle(articleEntity = data)
            } else {
                createDefaultArticle(articleEntity = data)
            }
        }
        return articleItems
    }

    override fun buildCategoriesList(
        category: SearchNewsCategory,
        articleEntityItems: List<ArticleEntity>
    ): List<ArticleModel> {
        return articleEntityItems.map {
            createCategoryArticle(
                articleEntity = it,
                category = category
            )
        }
    }

    private fun createDefaultArticle(articleEntity: ArticleEntity) {
        articleItems.add(
            ArticleModel.DefaultArticle(
                id = DEFAULT_ARTICLE_ID,
                articleData = createBaseArticleData(articleEntity)
            )
        )
    }

    private fun createInitialArticle(articleEntity: ArticleEntity) {
        articleItems.add(
            FIRST_INDEX, ArticleModel.InitialArticle(
                id = INITIAL_ARTICLE_ID,
                articleData = createBaseArticleData(articleEntity)
            )
        )
    }

    private fun createCategoryArticle(
        articleEntity: ArticleEntity,
        category: SearchNewsCategory
    ): ArticleModel {
        return ArticleModel.CategoryArticle(
            id = CATEGORY_ARTICLE_ID,
            articleData = createBaseArticleData(articleEntity),
            categoryName = TextProvider.Text(text = category.id)
        )
    }

    private fun createBaseArticleData(articleEntity: ArticleEntity): BaseArticleData {
        return BaseArticleData(
            itemId = articleEntity.id,
            image = IconProvider.Url(url = articleEntity.urlToImage.toString()),
            title = TextProvider.Text(text = articleEntity.title.toString()),
            description = TextProvider.Text(text = articleEntity.description.toString()),
            author = TextProvider.Text(text = articleEntity.author.toString()),
            publishedAt = TextProvider.Text(text = articleEntity.publishedAt.toString()),
            urlToSource = TextProvider.Text(text = articleEntity.url.toString()),
            content = TextProvider.Text(text = articleEntity.content.toString()),
            sourceId = TextProvider.Text(text = articleEntity.sourceId.toString()),
            sourceName = TextProvider.Text(text = articleEntity.sourceName.toString()),
            isFavorite = articleEntity.isFavorite
        )
    }

    companion object {
        const val FIRST_INDEX = 0
    }
}