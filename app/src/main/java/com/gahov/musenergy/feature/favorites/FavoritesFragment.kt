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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
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
import com.gahov.musenergy.R
import com.gahov.musenergy.arch.ktx.getString
import com.gahov.musenergy.arch.ui.view.model.IconProvider
import com.gahov.musenergy.feature.articles.model.ArticleModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    //TODO it should be in another place
    private val circularXXFontFamily = FontFamily(
        Font(R.font.circular_xx_book, FontWeight.Normal),
        Font(R.font.circular_xx_medium, FontWeight.Medium),
        Font(R.font.circular_xx_bold, FontWeight.Bold)
    )

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
        val imageUrl = (data.articleData.image as IconProvider.Url).url
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            modifier = Modifier
                .padding(top = 16.dp, start = 25.dp, end = 25.dp, bottom = 5.dp)
                .height(325.dp)
                .fillMaxWidth()
        ) {
            Column {
                AsyncImage(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    model = imageUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Image"
                )
                Text(
                    text = data.articleData.sourceName.getString(requireContext()),
                    Modifier.padding(start = 25.dp, end = 25.dp, top = 20.dp, bottom = 2.dp),
                    maxLines = 1,
                    style = TextStyle(
                        fontFamily = circularXXFontFamily,
                        fontStyle = FontStyle.Normal,
                        fontSize = 14.sp,
                        color = Color(0xff8d9199),
                    )
                )
                Text(
                    modifier = Modifier.padding(
                        start = 25.dp,
                        top = 10.dp,
                        end = 25.dp,
                        bottom = 12.dp
                    ),
                    text = data.articleData.title.getString(requireContext()),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    softWrap = true,
                    textAlign = TextAlign.Left,
                    style = TextStyle(
                        fontFamily = circularXXFontFamily,
                        fontSize = 24.sp,
                        color = Color(0xFF202226),
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}