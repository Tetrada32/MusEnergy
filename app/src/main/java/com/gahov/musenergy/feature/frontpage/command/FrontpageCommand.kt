package com.gahov.musenergy.feature.frontpage.command

import com.gahov.domain.entities.news.ArticleEntity
import com.gahov.musenergy.arch.router.command.Command

sealed class FrontpageCommand : Command.FeatureCommand() {

    data class OnReloadEvent(val isSuccess: Boolean?) : FrontpageCommand()

    data class DisplayContent(val content: List<ArticleEntity>) : FrontpageCommand()

    data class OnOpenInBrowser(val url: String) : FrontpageCommand()

    data class NetworkError(val error: Error) : FrontpageCommand()

    object HideKeyboard : FrontpageCommand()
}