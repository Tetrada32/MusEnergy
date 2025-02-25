package com.gahov.musenergy.feature.frontpage.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.gahov.musenergy.R
import com.gahov.musenergy.arch.ui.recycler.BaseRecyclerListAdapter
import com.gahov.musenergy.arch.ui.recycler.BaseViewHolder
import com.gahov.musenergy.feature.articles.ArticleViewType
import com.gahov.musenergy.feature.articles.model.ArticleModel
import com.gahov.musenergy.feature.frontpage.adapter.viewholder.CategoryArticleViewHolder
import com.gahov.musenergy.feature.frontpage.adapter.viewholder.DefaultArticleViewHolder
import com.gahov.musenergy.feature.frontpage.adapter.viewholder.InitialArticleViewHolder
import com.gahov.musenergy.feature.articles.presenter.ArticleHolderPresenter

class ArticleListAdapter(
    private val presenter: ArticleHolderPresenter
) : BaseRecyclerListAdapter<ArticleModel>(ArticleDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ArticleModel, out ViewDataBinding> {
        return when (ArticleViewType.entries[viewType]) {
            ArticleViewType.INITIAL_ARTICLE -> InitialArticleViewHolder(
                presenter = presenter,
                binding = inflate(parent, R.layout.item_frontpage_initial_article)
            )

            ArticleViewType.CATEGORY_ARTICLE -> CategoryArticleViewHolder(
                presenter = presenter,
                binding = inflate(parent, R.layout.item_category_article)
            )

            ArticleViewType.DEFAULT_ARTICLE -> DefaultArticleViewHolder(
                presenter = presenter,
                binding = inflate(parent, R.layout.item_default_article)
            )
        }
    }

    override fun getItemViewType(position: Int) = getItem(position).getModelType().ordinal
}