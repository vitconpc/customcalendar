package com.rantea.animeowm.adapter.fast

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.rantea.animeowm.adapter.fast.anim.ExpandBinder
import java.util.*

open class FastAdapter<T>(
    val items: MutableList<T>,
    var onBindView: OnBindView?,
    vararg var layoutIds: Int,
    var expandViewId: Int = 0
) : RecyclerView.Adapter<FastViewHolder>(), Filterable {

    val itemsBackup by lazy {
        mutableListOf<T>().apply { addAll(items) }
    }
    var expandBinder: ExpandBinder<FastViewHolder>? = null
    private val fastAdapterFilter = FastAdapterFilter()

    init {
        if (expandViewId != 0) expandBinder = ExpandBinder()
        onBindView?.expandBinder = expandBinder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FastViewHolder {
        val viewHolder = FastViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        layoutIds[viewType],
                        parent,
                        false
                )
        ).apply {
            expandView = itemView.findViewById(expandViewId)
        }
        return viewHolder
    }

    override fun getItemViewType(position: Int): Int {
        return onBindView?.getViewType(position) ?: 0
    }

    override fun onBindViewHolder(holder: FastViewHolder, position: Int) {
        expandBinder?.bind(holder, position)
        onBindView?.onBindView(holder, position)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getFilter(): Filter {
        return fastAdapterFilter
    }

    open fun clearFilter() {
        if (items.size != itemsBackup.size)
            items.addAll(itemsBackup)
    }

    inner class FastAdapterFilter : Filter() {

        override fun performFiltering(constraint: CharSequence): FilterResults? {
            val condition = constraint.toString().toLowerCase(Locale.ROOT)
            val results = FilterResults()
            for (item in itemsBackup)
                if (onBindView?.onItemFilter(item, condition) ?: true)
                    items.add(item)
            results.count = items.size
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            notifyDataSetChanged()
        }

    }
}