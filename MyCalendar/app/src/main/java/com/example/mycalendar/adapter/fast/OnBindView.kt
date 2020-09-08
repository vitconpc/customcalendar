package com.rantea.animeowm.adapter.fast

abstract class OnBindView {

    abstract fun onBindView(holder: FastViewHolder, position: Int)

    open fun getViewType(position: Int): Int = 0

    open fun onItemFilter(item: Any?, condition: String): Boolean = false

}