package com.gahov.musenergy.feature.favorites.command

import com.gahov.musenergy.arch.router.command.Command
import com.gahov.musenergy.feature.articles.model.ArticleModel

sealed class FavouritesCommand : Command.FeatureCommand() {

    data class DisplayContent(val content: List<ArticleModel>) : FavouritesCommand()

    data class NetworkError(val error: Error) : FavouritesCommand()

}