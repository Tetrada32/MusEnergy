package com.gahov.musenergy.data.mapper.articles

import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.musenergy.data.local.entities.ArticleDTO
import com.gahov.musenergy.data.mapper.common.DbMapper

class ArticlesLocalMapper : DbMapper<ArticleEntity, ArticleDTO> {

    override fun toDatabase(domainModel: ArticleEntity): ArticleDTO {
        return ArticleDTO(
            id = domainModel.title?.hashCode()?.toLong() ?: 12L ,
            image = domainModel.urlToImage,
            title = domainModel.title,
            description = domainModel.description,
            author = domainModel.author,
            urlToSource = domainModel.url,
            publishedAt = domainModel.publishedAt,
            content = domainModel.content,
            sourceId = domainModel.sourceId,
            sourceName = domainModel.sourceName,
            isFavorite = domainModel.isFavorite
        )
    }

    override fun toDomain(dbModel: ArticleDTO): ArticleEntity {
        return ArticleEntity(
            id = dbModel.id,
            urlToImage = dbModel.image,
            title = dbModel.title,
            description = dbModel.description,
            author = dbModel.author,
            url = dbModel.urlToSource,
            publishedAt = dbModel.publishedAt,
            content = dbModel.content,
            sourceId = dbModel.sourceId,
            sourceName = dbModel.sourceName,
            isFavorite = dbModel.isFavorite
        )
    }

    override fun toDomain(dbModelList: List<ArticleDTO>?): List<ArticleEntity> {
        return dbModelList?.map { toDomain(it) } ?: emptyList()
    }

    override fun toDatabase(domainModelList: List<ArticleEntity>?): List<ArticleDTO> {
        return domainModelList?.map { toDatabase(it) } ?: emptyList()
    }
}