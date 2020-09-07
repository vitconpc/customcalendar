package com.rantea.animeowm.adapter.fast.anim

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.recyclerview.widget.RecyclerView

class ViewHolderAnimatorListener(private val _holder: RecyclerView.ViewHolder) : AnimatorListenerAdapter() {

    override fun onAnimationStart(animation: Animator) {
        _holder.setIsRecyclable(false)
    }

    override fun onAnimationEnd(animation: Animator) {
        _holder.setIsRecyclable(true)
    }

    override fun onAnimationCancel(animation: Animator) {
        _holder.setIsRecyclable(true)
    }
}

