package com.rantea.animeowm.adapter.fast.anim

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.View

object LayoutAnimator {

    class LayoutHeightUpdateListener(private val _view: View) : ValueAnimator.AnimatorUpdateListener {

        override fun onAnimationUpdate(animation: ValueAnimator) {
            val lp = _view.layoutParams
            lp.height = animation.animatedValue as Int
            _view.layoutParams = lp
        }

    }

    fun ofHeight(view: View, start: Int, end: Int): Animator {
        val animator = ValueAnimator.ofInt(start, end)
        animator.addUpdateListener(LayoutHeightUpdateListener(view))
        return animator
    }
}
