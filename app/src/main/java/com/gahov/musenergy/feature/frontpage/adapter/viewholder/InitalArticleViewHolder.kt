package com.gahov.musenergy.feature.frontpage.adapter.viewholder

import androidx.databinding.ViewDataBinding
import com.gahov.domain.entities.news.ArticleEntity
import com.gahov.musenergy.arch.component.coil.loadImage
import com.gahov.musenergy.arch.ui.recycler.BaseViewHolder
import com.gahov.musenergy.databinding.ItemFrontpageInitialArticleBinding
import com.gahov.musenergy.feature.frontpage.presenter.FrontpagePresenter

class InitalArticleViewHolder(
    private val presenter: FrontpagePresenter, binding: ViewDataBinding
) : BaseViewHolder<ArticleEntity, ItemFrontpageInitialArticleBinding>(binding) {

    override fun bindView(position: Int) {
        binding.presenter = presenter
        binding.article = item
        binding.frontpageInitialArticleImage.loadImage(url = item.urlToImage)
    }
}