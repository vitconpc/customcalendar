package com.rantea.animeowm.adapter.fast.anim

import androidx.recyclerview.widget.RecyclerView

class ExpandBinder<VH> where VH : RecyclerView.ViewHolder, VH : Expandable {

    private var _opened = -1

    fun bind(holder: VH, pos: Int) {
        holder.expandView?.let { expandView ->
            if (pos == _opened) ExpandableViewHoldersUtil.openH(holder, expandView, false)
            else ExpandableViewHoldersUtil.closeH(holder, expandView, false)
        }
    }

    fun bindSpecical(holder: VH, isOpen: Boolean) {
        holder.expandView?.let { expandView ->
            if (isOpen) ExpandableViewHoldersUtil.openH(holder, expandView, false)
            else ExpandableViewHoldersUtil.closeH(holder, expandView, false)
        }
    }

    fun toggle(holder: VH) {
        holder.expandView?.let { expandView ->
            if (_opened == holder.adapterPosition) {
                _opened = -1
                ExpandableViewHoldersUtil.closeH(holder, expandView, true)
            } else {
                val previous = _opened
                _opened = holder.adapterPosition
                ExpandableViewHoldersUtil.openH(holder, expandView, true)

                val oldHolder = (holder.itemView.parent as RecyclerView).findViewHolderForAdapterPosition(previous) as VH?
                oldHolder?.expandView?.let { oldexpandView ->
                    ExpandableViewHoldersUtil.closeH(oldHolder, oldexpandView, true)
                }
            }
        }
    }

    fun isOpen(holder: VH) = _opened == holder.adapterPosition
}
