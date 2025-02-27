package com.gahov.musenergy.data.mapper.articles

import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.musenergy.data.mapper.common.ApiMapper
import com.gahov.musenergy.data.remote.entities.article.ArticleResponse

class ArticlesRemoteMapper : ApiMapper<ArticleResponse, ArticleEntity> {
    override fun toDomain(apiModel: ArticleResponse): ArticleEntity {
        return ArticleEntity(
            sourceName = apiModel.sourceResponse?.name,
            sourceId = apiModel.sourceResponse?.id,
            author = apiModel.author,
            title = apiModel.title,
            description = apiModel.description,
            url = apiModel.url,
            urlToImage = apiModel.urlToImage,
            publishedAt = apiModel.publishedAt,
            content = apiModel.content,
            id = apiModel.title?.hashCode()?.toLong()
        )
    }

    override fun toDomain(list: List<ArticleResponse>?): List<ArticleEntity> {
        return list?.map { toDomain(apiModel = it) } ?: emptyList()
    }
}