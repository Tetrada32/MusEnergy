package com.gahov.musenergy.data.mapper.news

import com.gahov.domain.entities.news.ArticleEntity
import com.gahov.musenergy.data.mapper.common.ApiMapper
import com.gahov.musenergy.data.remote.entities.article.ArticleResponse

class ArticleRemoteMapper :
    ApiMapper<ArticleResponse, ArticleEntity> {
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
        )
    }
}