package com.gahov.musenergy.feature.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.gahov.musenergy.arch.ktx.getString
import com.gahov.musenergy.arch.ui.view.model.IconProvider
import com.gahov.musenergy.common.composable.Colors.BlueGray15
import com.gahov.musenergy.common.composable.Colors.BlueGray60
import com.gahov.musenergy.common.composable.Fonts.circularXXFontFamily
import com.gahov.musenergy.feature.articles.model.ArticleModel
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
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            modifier = Modifier
                .padding(top = 16.dp, start = 25.dp, end = 25.dp, bottom = 5.dp)
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Column(Modifier.padding(bottom = 9.dp)) {
                FavoritesImage((data.articleData.image as IconProvider.Url).url)
                ArticleSource(data.articleData.sourceName.getString(requireContext()))
                ArticleTitle(data.articleData.title.getString(requireContext()))
            }
        }
    }

    @Composable
    fun FavoritesImage(imageUrl: String) {
        AsyncImage(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            model = imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = "Image"
        )
    }

    @Composable
    fun ArticleSource(source: String) {
        Text(
            text = source,
            Modifier.padding(start = 25.dp, end = 25.dp, top = 20.dp),
            maxLines = 1,
            style = TextStyle(
                fontFamily = circularXXFontFamily,
                fontStyle = FontStyle.Normal,
                fontSize = 14.sp,
                color = BlueGray60,
            )
        )
    }

    @Composable
    fun ArticleTitle(title: String) {
        Text(
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 16.dp),
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
            )
        )
    }
}