package com.rantea.animeowm.adapter.fast

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class FastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun <T : View> findViewById(id: Int): T = itemView.findViewById(id)
}