package com.gahov.musenergy.common.extensions

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import com.gahov.musenergy.R


fun Context.shareWithUrl(text: String, url: String): Boolean {
    val intent = Intent()
    intent.action = Intent.ACTION_SEND
    intent.type = TEXT_INTENT_TYPE
    intent.putExtra(Intent.EXTRA_TEXT, "$text\n$url\n")
    return try {
        startActivity(Intent.createChooser(intent, this.resources.getString(R.string.Share)))
        true
    } catch (e: ActivityNotFoundException) {
        false
    }
}

const val TEXT_INTENT_TYPE = "text/plain"

