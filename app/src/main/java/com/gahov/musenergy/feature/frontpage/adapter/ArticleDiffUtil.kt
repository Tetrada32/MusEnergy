package com.gahov.musenergy.feature.frontpage.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gahov.domain.entities.news.ArticleEntity

class ArticleDiffUtil : DiffUtil.ItemCallback<ArticleEntity>() {

    override fun areItemsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
        return oldItem.areItemsSame(newItem)
    }

    override fun areContentsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
        return oldItem == newItem
    }

    private fun ArticleEntity.areItemsSame(newItem: ArticleEntity): Boolean {
        return this.title.equals(newItem.title)
    }
}