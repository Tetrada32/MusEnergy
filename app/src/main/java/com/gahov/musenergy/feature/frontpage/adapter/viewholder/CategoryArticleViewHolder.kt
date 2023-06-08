package com.gahov.musenergy.feature.frontpage.adapter.viewholder

import androidx.databinding.ViewDataBinding
import com.gahov.musenergy.arch.ui.recycler.BaseViewHolder
import com.gahov.musenergy.databinding.ItemCategoryArticleBinding
import com.gahov.musenergy.feature.articles.model.ArticleModel
import com.gahov.musenergy.feature.frontpage.presenter.ArticleHolderPresenter

class CategoryArticleViewHolder(
    private val presenter: ArticleHolderPresenter, binding: ViewDataBinding
) : BaseViewHolder<ArticleModel, ItemCategoryArticleBinding>(binding) {

    override fun bindView(position: Int) {
        val article = item as ArticleModel.CategoryArticle

        binding.presenter = presenter
        binding.article = article
    }
}