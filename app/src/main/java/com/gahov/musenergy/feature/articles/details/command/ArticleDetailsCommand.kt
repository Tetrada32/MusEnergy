package com.gahov.musenergy.feature.articles.details.command

import com.gahov.musenergy.arch.router.command.Command
import com.gahov.musenergy.arch.ui.view.model.TextProvider
import com.gahov.musenergy.feature.articles.model.ArticleModel

sealed class ArticleDetailsCommand : Command.FeatureCommand() {

    data class DisplayContent(val article: ArticleModel) : ArticleDetailsCommand()

    data class ShareArticle(val shareUrl: TextProvider) : ArticleDetailsCommand()

    data class OnOpenInBrowser(val sourceUrl: TextProvider) : ArticleDetailsCommand()
}