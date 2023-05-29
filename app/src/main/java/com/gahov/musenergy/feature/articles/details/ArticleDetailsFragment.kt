package com.gahov.musenergy.feature.articles.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.gahov.musenergy.R
import com.gahov.musenergy.arch.ktx.getString
import com.gahov.musenergy.arch.ui.fragment.BaseFragment
import com.gahov.musenergy.common.extensions.shareWithUrl
import com.gahov.musenergy.databinding.FragmentArticleDetailsBinding
import com.gahov.musenergy.feature.articles.model.ArticleModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailsFragment :
    BaseFragment<FragmentArticleDetailsBinding, ArticleDetailsViewModel>(
        contentLayoutID = R.layout.fragment_article_details,
        viewModelClass = ArticleDetailsViewModel::class.java
    ) {

    private val args: ArticleDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val articleDetails = args.article as ArticleModel.DefaultArticle
        binding.article = articleDetails
        binding.articleShareIcon.setOnClickListener {
            requireContext().apply {
                (shareWithUrl(
                    text = getString(R.string.share_message),
                    url = articleDetails.urlToSource.getString(this)
                ))
            }
        }
    }
}