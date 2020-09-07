package com.rantea.animeowm.adapter.fast.wheel

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class Wheel3DLayoutManager : LinearLayoutManager {

    private val mShrinkAmount = 0.35f
    private val mShrinkDistance = 0.9f
    val onBindWheelView: OnBindWheelView

    constructor(context: Context, onBindWheelView: OnBindWheelView) : super(context) {
        this.onBindWheelView = onBindWheelView
    }

//    override fun onLayoutCompleted(state: RecyclerView.State?) {
//        super.onLayoutCompleted(state)
//        scrollVerticallyBy(0, null, null)
//    }

    constructor(
        context: Context,
        orientation: Int,
        reverseLayout: Boolean,
        onBindWheelView: OnBindWheelView
    ) : super(
        context,
        orientation,
        reverseLayout
    ) {
        this.onBindWheelView = onBindWheelView
    }


    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val orientation = orientation
        if (orientation == VERTICAL) {
            val scrolled = super.scrollVerticallyBy(dy, recycler, state)
            val midpoint = height / 2f
            val d0 = 0f
            val d1 = mShrinkDistance * midpoint
            val s0 = 1f
            val s1 = 1f - mShrinkAmount
            for (i in 0 until childCount) {
                val child = getChildAt(i)
                val childMidpoint = (getDecoratedBottom(child!!) + getDecoratedTop(child)) / 2f
                val d = Math.min(d1, abs(midpoint - childMidpoint))
                val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)

                val dyP = (midpoint - childMidpoint) / midpoint
                //float ratePos = Math.abs(dyP);

                if (onBindWheelView.is3dMode) {
                    child.rotationX = onBindWheelView.rate3dAngleX * dyP
                    child.rotationY = onBindWheelView.rate3dAngleY * abs(dyP)
                    child.rotation = onBindWheelView.rate3dAngleZ * dyP
                    child.translationX = onBindWheelView.rateTransitionX * dyP * dyP

                    child.alpha = onBindWheelView.rateAlpha * (1f - abs(dyP))
                    child.scaleX = onBindWheelView.rateScaleX - abs(dyP) / 2
                    child.scaleY = onBindWheelView.rateScaleY * scale
                    //child.setTranslationZ(-100 * dyP * dyP);
                } else {
                    //child.setScaleX(scale);
                    child.scaleY = scale
                }
            }

            return scrolled
        } else {
            return 0
        }
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val orientation = orientation
        if (orientation == HORIZONTAL) {
            val scrolled = super.scrollHorizontallyBy(dx, recycler, state)

            val midpoint = width / 2f
            val d0 = 0f
            val d1 = mShrinkDistance * midpoint
            val s0 = 1f
            val s1 = 1f - mShrinkAmount
            for (i in 0 until childCount) {
                val child = getChildAt(i)
                val childMidpoint = (getDecoratedRight(child!!) + getDecoratedLeft(child)) / 2f
                val d = Math.min(d1, Math.abs(midpoint - childMidpoint))
                val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
                child.scaleX = scale
                //child.setScaleY(scale);
            }
            return scrolled
        } else {
            return 0
        }

    }
}