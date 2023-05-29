package com.gahov.musenergy.feature.articles.factory

import com.gahov.domain.entities.news.ArticleEntity
import com.gahov.musenergy.arch.ui.view.model.IconProvider
import com.gahov.musenergy.arch.ui.view.model.TextProvider
import com.gahov.musenergy.feature.articles.model.ArticleModel
import com.gahov.musenergy.feature.articles.model.ArticleModel.Companion.DEFAULT_ARTICLE_ID


class ArticleEntityToModelBuilder {

    private val articleItems: MutableList<ArticleModel> = mutableListOf()

    fun create(articleEntityItems: List<ArticleEntity>): List<ArticleModel> {
        articleItems.clear()
        articleEntityItems.map { data -> createDefaultArticle(articleEntity = data) }
        return articleItems
    }

    private fun createDefaultArticle(articleEntity: ArticleEntity) {
        with(articleEntity) {
            articleItems.add(
                ArticleModel.DefaultArticle(
                    id = DEFAULT_ARTICLE_ID,
                    image = IconProvider.Url(url = urlToImage.toString()),
                    title = TextProvider.Text(text = title.toString()),
                    description = TextProvider.Text(text = description.toString()),
                    author = TextProvider.Text(text = author.toString()),
                    publishedAt = TextProvider.Text(text = publishedAt.toString()),
                    urlToSource = TextProvider.Text(text = url.toString()),
                    content = TextProvider.Text(text = content.toString()),
                    sourceId = TextProvider.Text(text = sourceId.toString()),
                    sourceName = TextProvider.Text(text = sourceName.toString())
                )
            )
        }
    }
}