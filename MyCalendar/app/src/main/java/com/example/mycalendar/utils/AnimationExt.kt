package com.example.mycalendar.utils

import android.animation.Animator
import android.view.ViewPropertyAnimator

fun ViewPropertyAnimator.setEndListener(onEnd: (Animator) -> Unit = {}): ViewPropertyAnimator =
    setListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {
        }

        override fun onAnimationEnd(p0: Animator) {
            onEnd(p0)
        }

        override fun onAnimationCancel(p0: Animator?) {
        }

        override fun onAnimationStart(p0: Animator?) {
        }
    })
