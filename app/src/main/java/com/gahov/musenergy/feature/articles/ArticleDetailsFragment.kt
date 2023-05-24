package com.gahov.musenergy.feature.articles

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.gahov.musenergy.R
import com.gahov.musenergy.arch.component.coil.loadImage
import com.gahov.musenergy.arch.ui.fragment.BaseFragment
import com.gahov.musenergy.databinding.FragmentArticleDetailsBinding
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
        binding.article = args.article
        binding.articleDetailsImage.loadImage(args.article.urlToImage)
    }
}