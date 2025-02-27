package com.gahov.musenergy.common.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.core.content.res.ResourcesCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.load
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.gahov.musenergy.R


fun ImageView.loadImage(
    url: String?, crossfade: Boolean = true
) = load(url.toString()) {
    crossfade(crossfade)
    placeholder(getProgressPlaceholder(context))
}

fun ImageView.loadImage(
    drawable: Drawable, crossfade: Boolean = true
) = load(drawable) {
    val placeholder = getProgressPlaceholder(context)
    crossfade(crossfade)
    placeholder(placeholder)
    error(placeholder)
}

fun ImageView.loadImage(
    @DrawableRes resourceId: Int,
    crossfade: Boolean = true,
    placeholderParam: CircularProgressDrawable = getProgressPlaceholder(context)
) = load(resourceId) {
    crossfade(crossfade)
    placeholder(placeholderParam)
    error(placeholderParam)
}

@Composable
fun LoadImage(
    modifier: Modifier,
    url: String?,
    contentScale: ContentScale = ContentScale.Crop,
    crossfade: Boolean = true,
    placeholderParam: CircularProgressDrawable = getProgressPlaceholder(
        LocalPlatformContext.current
    )
) {
    val model = ImageRequest.Builder(LocalPlatformContext.current)
        .data(url)
        .crossfade(crossfade)
        .placeholder(placeholderParam)
        .error(placeholderParam)
        .build()

    AsyncImage(
        model = model,
        contentScale = contentScale,
        contentDescription = "Image",
        modifier = modifier,
    )
}

@Composable
fun LoadImage(
    modifier: Modifier,
    @DrawableRes resourceId: Int,
    contentScale: ContentScale = ContentScale.Crop,
    crossfade: Boolean = true,
    placeholderParam: CircularProgressDrawable = getProgressPlaceholder(
        LocalPlatformContext.current
    )
) {
    val model = ImageRequest.Builder(LocalPlatformContext.current)
        .data(resourceId)
        .crossfade(crossfade)
        .placeholder(placeholderParam)
        .error(placeholderParam)
        .build()

    AsyncImage(
        model = model,
        contentScale = contentScale,
        contentDescription = "Image",
        modifier = modifier,
    )
}

internal fun getProgressPlaceholder(context: Context): CircularProgressDrawable {
    val color = ResourcesCompat.getColor(context.resources, R.color.purple_500, context.theme)
    return CircularProgressDrawable(context).apply {
        setColorSchemeColors(color)
        start()
    }
}