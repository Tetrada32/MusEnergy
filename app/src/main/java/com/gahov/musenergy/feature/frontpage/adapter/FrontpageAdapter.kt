package com.gahov.musenergy.feature.frontpage.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.gahov.domain.entities.news.ArticleEntity
import com.gahov.musenergy.R
import com.gahov.musenergy.arch.ui.recycler.BaseRecyclerListAdapter
import com.gahov.musenergy.arch.ui.recycler.BaseViewHolder
import com.gahov.musenergy.feature.frontpage.adapter.viewholder.ArticleViewHolder
import com.gahov.musenergy.feature.frontpage.adapter.viewholder.InitalArticleViewHolder
import com.gahov.musenergy.feature.frontpage.presenter.FrontpagePresenter

class FrontpageAdapter(
    private val presenter: FrontpagePresenter
) : BaseRecyclerListAdapter<ArticleEntity>(ArticleDiffUtil()) {

    companion object {
        private const val VIEW_TYPE_INITIAL_ARTICLE = 0
        private const val VIEW_TYPE_DEFAULT_ARTICLE = 1
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ArticleEntity, out ViewDataBinding> {
        return when (viewType) {
            VIEW_TYPE_INITIAL_ARTICLE -> InitalArticleViewHolder(
                presenter = presenter,
                binding = inflate(parent, R.layout.item_frontpage_initial_article)
            )

            else -> ArticleViewHolder(
                presenter = presenter,
                binding = inflate(parent, R.layout.item_frontpage_article)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEW_TYPE_INITIAL_ARTICLE
        } else {
            VIEW_TYPE_DEFAULT_ARTICLE
        }
    }
}