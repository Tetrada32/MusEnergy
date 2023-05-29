package com.gahov.musenergy.feature.frontpage.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gahov.musenergy.feature.articles.model.ArticleModel

class ArticleDiffUtil : DiffUtil.ItemCallback<ArticleModel>() {

    override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
        return oldItem.areItemsSame(newItem)
    }

    override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
        return oldItem == newItem
    }
}