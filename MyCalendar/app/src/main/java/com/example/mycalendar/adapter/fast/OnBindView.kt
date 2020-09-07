package com.rantea.animeowm.adapter.fast

import com.rantea.animeowm.adapter.fast.anim.ExpandBinder

abstract class OnBindView {

    var expandBinder: ExpandBinder<FastViewHolder>? = null

    abstract fun onBindView(holder: FastViewHolder, position: Int)

    open fun getViewType(position: Int): Int = 0

    open fun onItemFilter(item: Any?, condition: String): Boolean = false

}