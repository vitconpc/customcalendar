package com.rantea.animeowm.adapter.fast.wheel

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

fun <T> RecyclerView.wheelAdapter(wheelAdapter: WheelAdapter<T>) {
    // Setting layout manager to recyclerView.
    val manager = WheelLayoutManager(
        context,
        LinearLayoutManager.VERTICAL,
        false,
        wheelAdapter.onBindWheelView
    )
    layoutManager = manager

    // Attaching SnapHelper to recyclerView.
    val snapHelper = LinearSnapHelper()
    snapHelper.attachToRecyclerView(this)

    // Listening to scroll events on RecyclerView.
    val scrollHandler = WheelScrollHandler(wheelAdapter, manager, snapHelper)
    addOnScrollListener(scrollHandler)
    adapter = wheelAdapter

    smoothScrollBy(0, -1)
    manager.scrollToPosition(wheelAdapter.onBindWheelView.focus)
//    manager.scrollVerticallyBy(0, null, null)
}

fun <T> RecyclerView.wheel3dAdapter(wheelAdapter: WheelAdapter<T>) {
    // Setting layout manager to recyclerView.
    val manager = Wheel3DLayoutManager(
        context,
        LinearLayoutManager.VERTICAL,
        false,
        wheelAdapter.onBindWheelView
    )
    layoutManager = manager

    // Attaching SnapHelper to recyclerView.
    val snapHelper = LinearSnapHelper()
    snapHelper.attachToRecyclerView(this)

    // Listening to scroll events on RecyclerView.
    val scrollHandler = WheelScrollHandler(wheelAdapter, manager, snapHelper)
    addOnScrollListener(scrollHandler)
    adapter = wheelAdapter

    smoothScrollBy(0, -1)
    manager.scrollToPosition(wheelAdapter.onBindWheelView.focus)
//    manager.scrollVerticallyBy(0, null, null)
}
