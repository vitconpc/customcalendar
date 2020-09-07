package com.rantea.animeowm.adapter.fast.anim

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View
import androidx.recyclerview.widget.RecyclerView

object ExpandableViewHoldersUtil {

    fun openH(holder: RecyclerView.ViewHolder, expandView: View, animate: Boolean) {
        if (animate) {
            expandView.visibility = View.VISIBLE
            val animator = ViewHolderAnimator.ofItemViewHeight(holder)
            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    val alphaAnimator = ObjectAnimator.ofFloat<View>(expandView, View.ALPHA, 1F)
                    alphaAnimator.addListener(ViewHolderAnimatorListener(holder))
                    alphaAnimator.start()
                }
            })
            animator.start()
        } else {
            expandView.visibility = View.VISIBLE
            expandView.alpha = 1f
        }
    }

    fun closeH(holder: RecyclerView.ViewHolder, expandView: View, animate: Boolean) {
        if (animate) {
            expandView.visibility = View.GONE
            val animator = ViewHolderAnimator.ofItemViewHeight(holder)
            expandView.visibility = View.VISIBLE
            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    expandView.visibility = View.GONE
                    expandView.alpha = 0f
                }

                override fun onAnimationCancel(animation: Animator) {
                    expandView.visibility = View.GONE
                    expandView.alpha = 0f
                }
            })
            animator.start()
        } else {
            expandView.visibility = View.GONE
            expandView.alpha = 0f
        }
    }


}
