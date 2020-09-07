package com.rantea.animeowm.adapter.fast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import java.util.*

open class FastSelectorAdapter<T>(
    var items: MutableList<T>,
    var onBindView: OnBindView?,
    var resLayout: Int,
    var idCheckbox: Int,
    var single: Boolean = false
) : RecyclerView.Adapter<FastSelectorAdapter.VH>() {

    var itemsSelected = ArrayList<T>()
    var lastCheck: CompoundButton? = null

    var onItemCheckedChangeListener: CompoundButton.OnCheckedChangeListener? = null

    protected open var updateState = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
        if (single) {
            if (buttonView === lastCheck) {
                setCheck(buttonView, true)
                notifyItemChanged(buttonView.id)
                onItemCheckedChangeListener?.onCheckedChanged(lastCheck, true)
            } else {
                if (lastCheck != null) {
                    itemsSelected.clear()
                    notifyItemChanged(lastCheck!!.id)
                }
                itemsSelected.add(items[buttonView.id])
                onItemCheckedChangeListener?.onCheckedChanged(lastCheck, false)
                onItemCheckedChangeListener?.onCheckedChanged(buttonView, true)
                lastCheck = buttonView
            }
        } else {
            if (isChecked) itemsSelected.add(items[buttonView.id])
            else itemsSelected.remove(items[buttonView.id])
            onItemCheckedChangeListener?.onCheckedChanged(buttonView, isChecked)
        }
    }

    protected fun setCheck(checkBox: CompoundButton, value: Boolean) {
        checkBox.setOnCheckedChangeListener(null)
        checkBox.isChecked = value
        checkBox.setOnCheckedChangeListener(updateState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val vh = object : VH(LayoutInflater.from(parent.context).inflate(resLayout, parent, false)) {}
        vh.checkBox = vh.itemView.findViewById(idCheckbox)
        return vh
    }

    override fun onBindViewHolder(holder: VH, i: Int) {
        holder.checkBox.id = i
        setCheck(holder.checkBox, itemsSelected.contains(items[i]))
        onBindView?.onBindView(0, i, holder)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(newData: MutableList<T>) {
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }

    open class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var checkBox: CompoundButton
    }

    abstract class OnBindView {
        abstract fun onBindView(type: Int, i: Int, holder: VH)

        open fun getViewType(position: Int): Int {
            return 0
        }
    }
}
