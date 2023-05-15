package com.gahov.musenergy.arch.ui.view.ktx

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.gahov.musenergy.arch.ktx.getString
import com.gahov.architecture.core.ui.view.model.TextProvider

@BindingAdapter("setText")
fun TextView.setText(textProvider: TextProvider?) {
    textProvider?.let { text = textProvider.getString(context) }

}