package com.gahov.musenergy.arch.ktx

import android.content.Context
import com.gahov.musenergy.arch.ui.view.model.TextProvider

fun TextProvider.getString(context: Context) = when (this) {
    is TextProvider.Text -> text
    is TextProvider.ResText -> if (text == 0) "" else context.getString(text)
    is TextProvider.FormatResText -> if (res == 0) "" else context.getString(res, text)
}