package com.gahov.musenergy.common.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openInBrowser(url: String) {
    try {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}