package com.gahov.musenergy.feature.frontpage.adapter.viewholder

import androidx.databinding.ViewDataBinding
import com.gahov.domain.entities.news.ArticleEntity
import com.gahov.musenergy.arch.component.coil.loadImage
import com.gahov.musenergy.arch.ui.recycler.BaseViewHolder
import com.gahov.musenergy.databinding.ItemFrontpageArticleBinding
import com.gahov.musenergy.feature.frontpage.presenter.FrontpagePresenter

class ArticleViewHolder(
    private val presenter: FrontpagePresenter, binding: ViewDataBinding
) : BaseViewHolder<ArticleEntity, ItemFrontpageArticleBinding>(binding) {

    override fun bindView(position: Int) {
        binding.presenter = presenter
        binding.article = item
        binding.frontpageArticleImage.loadImage(url = item.urlToImage)
    }
}