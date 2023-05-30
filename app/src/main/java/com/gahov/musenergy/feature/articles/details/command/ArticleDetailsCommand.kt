package com.gahov.musenergy.feature.articles.details.command

import com.gahov.musenergy.arch.router.command.Command
import com.gahov.musenergy.arch.ui.view.model.TextProvider

sealed class ArticleDetailsCommand : Command.FeatureCommand() {

    data class ShareArticle(val shareUrl: TextProvider) : ArticleDetailsCommand()

    data class OnOpenInBrowser(val sourceUrl: TextProvider) : ArticleDetailsCommand()
}