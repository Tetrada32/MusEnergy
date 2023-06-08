package com.gahov.musenergy.feature.frontpage.command

import com.gahov.musenergy.arch.router.command.Command
import com.gahov.musenergy.feature.articles.model.ArticleModel

sealed class FrontpageCommand : Command.FeatureCommand() {

    data class DisplayContent(val content: List<ArticleModel>) : FrontpageCommand()

    data class NetworkError(val error: Error) : FrontpageCommand()

    object HideKeyboard : FrontpageCommand()
}