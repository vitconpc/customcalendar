package com.rantea.animeowm.adapter.fast.wheel

import android.view.View
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

class WheelScrollHandler<T>(
    var adapter: WheelAdapter<T>,
    var manager: RecyclerView.LayoutManager,
    var snapHelper: LinearSnapHelper
) : RecyclerView.OnScrollListener() {

    var lastView: View? = null
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        snapHelper.findSnapView(manager)?.let { centerView ->
            //normal format
            lastView?.let { adapter.onBindWheelView.onBindUnselectItem(it) }
            // position of the center item
            val selectedPosition = manager.getPosition(centerView)
            //center format
            adapter.onBindWheelView.onBindSelectItem(centerView, selectedPosition)
            adapter.selectedPosition = selectedPosition
            if (adapter.items.isNotEmpty())
                adapter.selected = adapter.items[adapter.selectedPosition]
            lastView = centerView
        }
    }
}