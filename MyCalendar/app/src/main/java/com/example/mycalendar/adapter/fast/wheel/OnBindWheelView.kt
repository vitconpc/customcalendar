package com.rantea.animeowm.adapter.fast.wheel

import android.view.View
import com.rantea.animeowm.adapter.fast.FastViewHolder
import com.rantea.animeowm.adapter.fast.OnBindView

abstract class OnBindWheelView : OnBindView() {
    open var focus: Int = 0
    var is3dMode = true
    var rate3dAngleX = 45
    var rate3dAngleY = 0 //20
    var rate3dAngleZ = 0 //20
    var rateTransitionX = 0f //20f
    var rateAlpha = 1f //1f
    var rateScaleX = 1f //1f
    var rateScaleY = 1f //1f


    override fun onBindView(holder: FastViewHolder, position: Int) {
        onBindUnselectItem(holder.itemView)
    }

    open fun onBindSelectItem(view: View, pos: Int) {}

    open fun onBindUnselectItem(view: View) {}
}
