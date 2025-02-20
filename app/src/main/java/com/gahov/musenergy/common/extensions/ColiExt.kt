package com.gahov.musenergy.common.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil3.load
import coil3.request.crossfade
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
    error(placeholder)
    placeholder(placeholder)
}

fun ImageView.loadImage(
    @DrawableRes resourceId: Int,
    crossfade: Boolean = true,
    placeholderParam: CircularProgressDrawable = getProgressPlaceholder(context)
) = load(resourceId) {
    crossfade(crossfade)
    error(placeholderParam)
    placeholder(placeholderParam)
}

internal fun getProgressPlaceholder(context: Context): CircularProgressDrawable {
    val color = ResourcesCompat.getColor(context.resources, R.color.purple_500, context.theme)
    return CircularProgressDrawable(context).apply {
        setColorSchemeColors(color)
        start()
    }
}