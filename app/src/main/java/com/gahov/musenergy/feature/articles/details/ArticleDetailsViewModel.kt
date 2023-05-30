package com.gahov.musenergy.feature.articles.details

import com.gahov.musenergy.arch.controller.BaseViewModel
import com.gahov.musenergy.feature.articles.details.command.ArticleDetailsCommand
import com.gahov.musenergy.feature.articles.details.presenter.ArticleDetailsPresenter
import com.gahov.musenergy.feature.articles.model.ArticleModel
import javax.inject.Inject

class ArticleDetailsViewModel @Inject constructor() : BaseViewModel(), ArticleDetailsPresenter {

    override fun onShareIconClick(article: ArticleModel) {
        val url = (article as ArticleModel.DefaultArticle).urlToSource
        handleCommand(ArticleDetailsCommand.ShareArticle(url))
    }

    override fun onOpenInBrowserClick(article: ArticleModel) {
        val url = (article as ArticleModel.DefaultArticle).urlToSource
        handleCommand(ArticleDetailsCommand.OnOpenInBrowser(url))
    }
}