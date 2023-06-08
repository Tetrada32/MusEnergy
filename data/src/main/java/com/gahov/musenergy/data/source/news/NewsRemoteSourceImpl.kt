package com.gahov.musenergy.data.source.news

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.search.SearchNewsCategory
import com.gahov.musenergy.data.remote.call
import com.gahov.musenergy.data.remote.entities.success.ArticleSuccessResponse
import com.gahov.musenergy.data.remote.protocol.MainProtocol

class NewsRemoteSourceImpl(
    private val protocol: MainProtocol,
) : NewsRemoteSource {

    override suspend fun loadEverything(): Either<Failure, ArticleSuccessResponse> {
        return call { protocol.getNewsList(SearchNewsCategory.CATEGORY_ALL.id) }
    }

    override suspend fun loadCategoryNews(category: SearchNewsCategory): Either<Failure, ArticleSuccessResponse> {
        val musicParam = "$MUSIC_TAG ${category.id}"
        return call { protocol.getNewsList(musicParam) }
    }

    companion object {
        const val MUSIC_TAG = "music"
    }
}