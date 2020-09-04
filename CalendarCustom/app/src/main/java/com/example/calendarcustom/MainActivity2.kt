package com.example.calendarcustom

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.calendarcustom.lib2.data.CalendarAdapter
import com.example.calendarcustom.lib2.data.Day
import com.example.calendarcustom.lib2.widget.FlexibleCalendar
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val viewCalendar = findViewById(R.id.calendar) as FlexibleCalendar
        val cal: Calendar = Calendar.getInstance()
        val adapter = CalendarAdapter(this, cal)
        viewCalendar.setAdapter(adapter)

        btn_collapse.setOnClickListener {
            viewCalendar.collapse(500)
        }
        btn_expand.setOnClickListener {
            viewCalendar.expand(500)
        }


        // bind events of calendar
        viewCalendar.setCalendarListener(object : FlexibleCalendar.CalendarListener {
            override fun onDaySelect() {
                val day: Day = viewCalendar.selectedDay
                Log.i(
                    javaClass.name, ("Selected Day: "
                            + day.getYear()) + "/" + (day.getMonth() + 1).toString() + "/" + day.getDay()
                )
            }

            override fun onItemClick(v: View?) {
                val day: Day = viewCalendar.selectedDay
                Log.i(
                    javaClass.name, ("The Day of Clicked View: "
                            + day.getYear()) + "/" + (day.getMonth() + 1).toString() + "/" + day.getDay()
                )
            }

            override fun onDataUpdate() {
                Log.i(javaClass.name, "Data Updated")
            }

            override fun onMonthChange() {
                Log.i(
                    javaClass.name, "Month Changed"
                            + ". Current Year: " + viewCalendar.year
                            + ", Current Month: " + (viewCalendar.month + 1)
                )
            }

            override fun onWeekChange(position: Int) {
                Log.i(
                    javaClass.name, "Week Changed"
                            + ". Current Year: " + viewCalendar.year
                            + ", Current Month: " + (viewCalendar.month + 1)
                            + ", Current Week position of Month: " + position
                )
            }
        })

        // use methods

        // use methods
        viewCalendar.addEventTag(2020, 9, 10)
        viewCalendar.addEventTag(2020, 9, 14)
        viewCalendar.addEventTag(2020, 9, 23)

        viewCalendar.select(Day(2020, 9, 22))
    }
}