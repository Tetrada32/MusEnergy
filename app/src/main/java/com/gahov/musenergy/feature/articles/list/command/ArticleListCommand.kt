package com.gahov.musenergy.feature.articles.list.command

import com.gahov.musenergy.arch.router.command.Command
import com.gahov.musenergy.feature.articles.model.ArticleModel

sealed class ArticleListCommand : Command.FeatureCommand() {

    data class DisplayContent(val content: List<ArticleModel>) : ArticleListCommand()

    data class NetworkError(val error: Error) : ArticleListCommand()

    object HideKeyboard : ArticleListCommand()
}