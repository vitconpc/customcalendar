package com.rantea.animeowm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class LineListView<T : LineListView.ViewHolder>(var vg: ViewGroup) {

    var vhs = ArrayList<T>()

    abstract fun itemCount(): Int

    val context: Context
        get() = vg.context

    init {
        init(vg)
        //call create to start display
    }

    fun init(vg: ViewGroup) {}

    fun create(): LineListView<*> {
        clear()
        val inflater = LayoutInflater.from(vg.context)
        for (i in 0 until itemCount()) {
            val vh = createItem(i, inflater)
            vg.addView(vh.itemView)
            vhs.add(vh)
            update(i)
        }
        return this
    }

    abstract fun createItem(i: Int, inflater: LayoutInflater): T

    //like bindview
    abstract fun update(i: Int)

//    fun updateAll() {
//        if (getVhs().size > itemCount()) {
//            create()
//            return
//        }
//        if (getVhs().size < itemCount()) updateNew()
//        for (i in 0 until itemCount()) update(i)
//    }

//    fun updateNew() {
//        val nvh = vhs.size
//        val inflater = LayoutInflater.from(vg.context)
//        for (i in nvh until itemCount()) {
//            val vh = createItem(i, inflater)
//            vg.addView(vh.itemView)
//            vhs.add(vh)
//            update(i)
//        }
//    }

    fun getVH(i: Int): T {
        return getVhs()[i]
    }

    fun getVhs(): List<T> {
        return vhs
    }

    fun clear() {
        vg.removeAllViews()
        vhs.clear()
    }

    open class ViewHolder(var itemView: View)
}