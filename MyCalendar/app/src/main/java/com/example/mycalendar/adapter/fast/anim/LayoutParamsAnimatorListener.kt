package com.rantea.animeowm.adapter.fast.anim

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

class LayoutParamsAnimatorListener(
    private val _view: View,
    private val _paramsWidth: Int,
    private val _paramsHeight: Int
) : AnimatorListenerAdapter() {

    override fun onAnimationEnd(animation: Animator) {
        val params = _view.layoutParams
        params.width = _paramsWidth
        params.height = _paramsHeight
        _view.layoutParams = params
    }
}

