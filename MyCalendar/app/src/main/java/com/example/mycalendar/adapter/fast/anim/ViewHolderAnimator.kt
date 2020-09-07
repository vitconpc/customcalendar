package com.rantea.animeowm.adapter.fast.anim

import android.animation.Animator
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

object ViewHolderAnimator {

    fun ofItemViewHeight(holder: RecyclerView.ViewHolder): Animator {
        val parent = holder.itemView.parent as View

        val start = holder.itemView.measuredHeight
        holder.itemView.measure(
            View.MeasureSpec.makeMeasureSpec(parent.measuredWidth, View.MeasureSpec.AT_MOST),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        val end = holder.itemView.measuredHeight

        val animator = LayoutAnimator.ofHeight(holder.itemView, start, end)
        animator.addListener(ViewHolderAnimatorListener(holder))

        animator.addListener(
                LayoutParamsAnimatorListener(
                        holder.itemView,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        )

        return animator
    }

}
