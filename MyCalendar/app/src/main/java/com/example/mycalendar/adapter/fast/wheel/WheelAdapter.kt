package com.rantea.animeowm.adapter.fast.wheel

import com.rantea.animeowm.adapter.fast.FastAdapter

open class WheelAdapter<T>(
    items: MutableList<T>,
    var onBindWheelView: OnBindWheelView,
    vararg layoutIds: Int
) : FastAdapter<T>(items, onBindWheelView, *layoutIds) {
    open var selected: T? = null
    open var selectedPosition: Int = -1
}

