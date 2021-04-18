package com.fabiolee.iproperty.ui

import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("html")
fun TextView.setHtml(html: String?) {
    if (html.isNullOrEmpty()) {
        text = html
    } else {
        val source = html.replace(" ", "&nbsp;")
            .replace("\r\n", "<br />")
        text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(source, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(source)
        }
    }
}