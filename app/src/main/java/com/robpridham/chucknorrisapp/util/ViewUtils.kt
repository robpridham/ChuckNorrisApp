package com.robpridham.chucknorrisapp.util

import android.animation.Animator
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.TextView

fun TextView.setTextFromHtml(html: String) {
    this.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(html)
    }
}

private const val ANIMATION_DEFAULT_DURATION = 300L

fun View.fadeIn(duration: Long = ANIMATION_DEFAULT_DURATION) {
    val animator = animate()
    animator.cancel()
    alpha = 0.0f
    visibility = View.VISIBLE
    animator.duration = duration
    animator.alpha(1.0f)
}

fun View.fadeOutIfVisible() {
    if (visibility == View.VISIBLE) {
        fadeOut()
    }
}

fun View.fadeOut(duration: Long = ANIMATION_DEFAULT_DURATION) {
    val animator = animate()
    animator.cancel()
    alpha = 1.0f
    val listener: Animator.AnimatorListener = object : Animator.AnimatorListener {

        override fun onAnimationRepeat(animation: Animator?) {}

        override fun onAnimationCancel(animation: Animator?) {}

        override fun onAnimationStart(animation: Animator?) {}
        override fun onAnimationEnd(animation: Animator?) {
            visibility = View.INVISIBLE
            animate().setListener(null)
        }
    }
    animator.duration = duration
    animator.setListener(listener)
    animator.alpha(0.0f)
}