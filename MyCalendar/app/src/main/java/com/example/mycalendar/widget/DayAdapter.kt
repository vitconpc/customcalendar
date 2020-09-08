package com.example.mycalendar.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycalendar.R
import com.example.mycalendar.adapter.fast.FastViewHolder
import com.example.mycalendar.utils.setEndListener
import kotlinx.android.synthetic.main.item_month.view.*
import java.util.*

class DayAdapter(
    private val calendar: Calendar,
    private val number: Int,
    private val month: Int = 0,
    private val startDOW: Int = 0
) : RecyclerView.Adapter<FastViewHolder>() {
    val holders = mutableListOf<FastViewHolder>()

    var notifyAdapter: (Calendar) -> Unit = {}
    lateinit var refreshCalendar: Calendar

    var animationOn: Boolean = false
    var animationLine: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FastViewHolder {
        return FastViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
        )
    }

    override fun getItemCount(): Int = number

    override fun onBindViewHolder(holder: FastViewHolder, position: Int) {
        if (!holders.contains(holder)) {
            holder.itemView.tag = position
            holders.add(holder)
        }
        if (number != WEEK_MODE_NUMBER) {
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, position - startDOW)
            holder.itemView.tv_test.text = calendar.get(Calendar.DAY_OF_MONTH).toString()

            holder.itemView.tv_test.alpha =
                if (calendar.get(Calendar.MONTH) == month) 1f else 0.3f
        } else {
            calendar.set(Calendar.DAY_OF_WEEK, position + 1)
            holder.itemView.tv_test.text = calendar.get(Calendar.DAY_OF_MONTH).toString()
        }
        if (animationOn)
            animationMonthToWeek(holder, refreshCalendar, position)
    }

    fun setAnimationMonthToWeek(
        notifRefreshAdapter: (Calendar) -> Unit,
        calendar: Calendar,
        animationLine: Int
    ) {
        this.notifyAdapter = notifRefreshAdapter
        this.refreshCalendar = calendar
        this.animationLine = animationLine
        this.animationOn = true
    }

    private fun animationMonthToWeek(
        holder: FastViewHolder,
        calendar: Calendar,
        pos: Int
    ) {
        if (pos < WEEK_MODE_NUMBER) return
        val itemHeight = holder.itemView.context.resources.getDimension(R.dimen._30dp)
        val ty = itemHeight * (pos / WEEK_MODE_NUMBER)

        holder.itemView.alpha = 0f
        holder.itemView.translationY = -ty

        val anim = holder.itemView.animate()
            .translationYBy(ty)
            .alpha(1f)
            .setDuration(1000)

        if (pos == number - 1) {
            animationOn = false
            anim.setEndListener { notifyAdapter(calendar) }.start()
        } else anim.start()
    }

    companion object {
        const val WEEK_MODE_NUMBER = 7
    }
}