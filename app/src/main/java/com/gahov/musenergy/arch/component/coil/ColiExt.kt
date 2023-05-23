package com.gahov.musenergy.arch.component.coil

import android.content.Context
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.gahov.musenergy.R


fun ImageView.loadImage(
    url: String?, crossfade: Boolean = true
) = load(url.toString()) {
    crossfade(crossfade)
    placeholder(getProgressPlaceholder(context))
}

internal fun getProgressPlaceholder(context: Context): CircularProgressDrawable {
    val color = ResourcesCompat.getColor(context.resources, R.color.purple_500, context.theme)
    return CircularProgressDrawable(context).apply {
        setColorSchemeColors(color)
        start()
    }
}