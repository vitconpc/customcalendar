package com.rantea.animeowm.adapter.fast

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rantea.animeowm.adapter.fast.anim.Expandable

open class FastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), Expandable {
    override var expandView: View? = null

    fun <T : View> findViewById(id: Int): T = itemView.findViewById(id)
}