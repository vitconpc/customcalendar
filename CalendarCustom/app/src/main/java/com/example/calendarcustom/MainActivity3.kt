package com.example.calendarcustom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calendarcustom.CustomPagerAdapter.Companion.FEB
import com.example.calendarcustom.CustomPagerAdapter.Companion.MONTH_MAX
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        view_pager_2.adapter = CustomPagerAdapter()
        view_pager_2.setCurrentItem(2020 * MONTH_MAX + FEB, false)
    }
}