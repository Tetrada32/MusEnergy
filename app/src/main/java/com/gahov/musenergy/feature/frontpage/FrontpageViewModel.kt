package com.gahov.musenergy.feature.frontpage

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.news.ArticleEntity
import com.gahov.domain.usecase.news.frontpage.LoadFrontpageUseCase
import com.gahov.musenergy.arch.controller.BaseViewModel
import com.gahov.musenergy.data.local.entities.TokenData
import com.gahov.musenergy.data.remote.configuration.interceptor.utils.token.TokenProvider
import com.gahov.musenergy.feature.frontpage.command.FrontpageCommand
import com.gahov.musenergy.feature.frontpage.presenter.FrontpagePresenter
import javax.inject.Inject

class FrontpageViewModel @Inject constructor(
    private val loadFrontpageUseCase: LoadFrontpageUseCase,
    private val logger: com.gahov.domain.component.logger.Logger,
    private val tokenProvider: TokenProvider
) : BaseViewModel(), FrontpagePresenter {

    companion object {
        const val API_TOKEN = "fe7411ed99314e8e9200167d8cde676f"
    }

    init {
        saveDefaultToken()
        launch { loadFrontpageContent() }
    }

    //TODO TEMP
    private fun saveDefaultToken() {
        with(tokenProvider) {
            if (getToken().isNullOrBlank()) {
                setToken(TokenData(accessToken = API_TOKEN))
            }
        }
    }

    private suspend fun loadFrontpageContent() {
        when (val result = loadFrontpageUseCase.execute(param = null)) {
            is Either.Right -> handleCommand(FrontpageCommand.DisplayContent(content = result.success))
            is Either.Left -> logger.log(message = "Failure: \n ${result.failure}")
        }
    }

    override fun onArticleClick(article: ArticleEntity) {
        navigateDirection(FrontpageFragmentDirections.actionFrontpageToArticleDetails(article))
    }
}