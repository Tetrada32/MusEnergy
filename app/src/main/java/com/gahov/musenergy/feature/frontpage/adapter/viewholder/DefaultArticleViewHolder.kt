package com.gahov.musenergy.feature.frontpage.adapter.viewholder

import androidx.databinding.ViewDataBinding
import com.gahov.musenergy.arch.ui.recycler.BaseViewHolder
import com.gahov.musenergy.databinding.ItemDefaultArticleBinding
import com.gahov.musenergy.feature.articles.model.ArticleModel
import com.gahov.musenergy.feature.articles.presenter.ArticleHolderPresenter

class DefaultArticleViewHolder(
    private val presenter: ArticleHolderPresenter, binding: ViewDataBinding
) : BaseViewHolder<ArticleModel, ItemDefaultArticleBinding>(binding) {

    override fun bindView(position: Int) {
        val article = item as ArticleModel.DefaultArticle

        binding.presenter = presenter
        binding.article = article
    }
}