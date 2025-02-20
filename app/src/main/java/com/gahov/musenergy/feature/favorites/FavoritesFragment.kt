package com.gahov.musenergy.feature.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.gahov.musenergy.arch.ktx.getString
import com.gahov.musenergy.feature.articles.model.ArticleModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                FavoritesScreen()
            }
        }
    }

    @Preview
    @Composable
    fun FavoritesScreen() {
        val viewModel: FavoritesViewModel = hiltViewModel()
        val favourites = viewModel.favouritesArticles.observeAsState().value ?: emptyList()

        LazyColumn(Modifier.fillMaxSize()) {
            items(favourites) {
                FavoriteItem(it)
            }
        }
    }

    @Composable
    fun FavoriteItem(data: ArticleModel) {
        Card {
            Row(modifier = Modifier.fillMaxSize()) {
                AsyncImage(model = data.articleData.image, contentDescription = "Image")
                Text(text = data.articleData.title.getString(requireContext()))
            }
        }
    }
}