package com.gahov.musenergy.feature.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.gahov.domain.entities.articles.ArticleEntity
import com.gahov.domain.entities.articles.ArticleEntity.Companion.PLACEHOLDER_IMAGE
import com.gahov.musenergy.common.composable.Colors.BlueGray15
import com.gahov.musenergy.common.composable.Colors.BlueGray60
import com.gahov.musenergy.common.composable.Fonts.circularXXFontFamily
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            FavoritesScreen()
        }
    }

    @Composable
    fun FavoritesScreen(viewModel: FavoritesViewModel = hiltViewModel()) {
        val favourites by viewModel.favouritesArticles.observeAsState(emptyList())

        if (favourites.isEmpty()) {
            EmptyState()
        } else {
            LazyColumn(Modifier.fillMaxSize().padding(top = 16.dp)) {
                items(favourites) { article ->
                    FavoriteItem(
                        article = article,
                        onRemove = { viewModel.removeFromFavorites(article) }
                    )
                }
            }
        }
    }

    @Composable
    fun FavoriteItem(article: ArticleEntity, onRemove: () -> Unit, modifier: Modifier = Modifier) {
        ElevatedCard(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = modifier
                .padding(horizontal = 25.dp, vertical = 12.dp)
                .fillMaxWidth()
        ) {
            Column(Modifier.padding(bottom = 9.dp)) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    FavoritesImage((article.urlToImage ?: PLACEHOLDER_IMAGE))
                    RemoveFavoriteArticleButton(
                        onClick = onRemove,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 8.dp, bottom = 12.dp)
                    )
                }
                ArticleSource(article.sourceName.toString())
                ArticleTitle(article.title.toString())
            }
        }
    }

    @Composable
    fun FavoritesImage(imageUrl: String) {
        AsyncImage(
            model = imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = "Favorite Article Image",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(bottom = 6.dp)
        )
    }

    @Composable
    fun ArticleSource(source: String) {
        Text(
            text = source,
            Modifier.padding(horizontal = 25.dp, vertical = 10.dp),
            maxLines = 1,
            style = TextStyle(
                fontFamily = circularXXFontFamily,
                fontSize = 14.sp,
                color = BlueGray60,
            )
        )
    }

    @Composable
    fun ArticleTitle(title: String) {
        Text(
            text = title,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            softWrap = true,
            textAlign = TextAlign.Left,
            style = TextStyle(
                fontFamily = circularXXFontFamily,
                fontSize = 24.sp,
                color = BlueGray15,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 16.dp)
        )
    }

    @Composable
    fun RemoveFavoriteArticleButton(onClick: () -> Unit, modifier: Modifier) {
        SmallFloatingActionButton(
            modifier = modifier,
            onClick = onClick,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(Icons.Filled.Favorite, "Remove article from favorites")
        }
    }

    @Composable
    fun EmptyState() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(25.dp),
                text = "No favorites so far! :) ",
                style = TextStyle(
                    fontFamily = circularXXFontFamily,
                    fontSize = 28.sp,
                    color = BlueGray15,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )

            AsyncImage(
                model = PLACEHOLDER_IMAGE,
                contentDescription = "No favorites",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Add articles you liked and get back to them here anytime, even offline!",
                style = TextStyle(
                    fontFamily = circularXXFontFamily,
                    fontSize = 15.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}
