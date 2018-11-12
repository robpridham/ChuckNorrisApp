package com.robpridham.chucknorrisapp.util

import android.os.Build
import android.text.Html
import android.widget.TextView

fun TextView.setTextFromHtml(html: String) {
    this.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(html)
    }
}