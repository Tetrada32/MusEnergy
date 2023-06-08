package com.gahov.musenergy.feature.frontpage.adapter.viewholder

import androidx.databinding.ViewDataBinding
import com.gahov.musenergy.arch.ui.recycler.BaseViewHolder
import com.gahov.musenergy.databinding.ItemFrontpageInitialArticleBinding
import com.gahov.musenergy.feature.articles.model.ArticleModel
import com.gahov.musenergy.feature.articles.presenter.ArticleHolderPresenter

class InitialArticleViewHolder(
    private val presenter: ArticleHolderPresenter, binding: ViewDataBinding
) : BaseViewHolder<ArticleModel, ItemFrontpageInitialArticleBinding>(binding) {

    override fun bindView(position: Int) {
        val article = item as ArticleModel.InitialArticle

        binding.presenter = presenter
        binding.article = article
    }
}