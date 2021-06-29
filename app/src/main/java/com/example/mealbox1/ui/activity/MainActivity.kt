package com.example.mealbox1.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.mealbox1.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue


class MainActivity : AppCompatActivity() {
    private var msg:String = ""
    private val viewPager: ViewPager2 by lazy {
        findViewById(R.id.mealTextView)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val format1 = SimpleDateFormat("yyyyMMdd")
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
                msg = format1.format(calendar.time)
                Log.d("date",msg)
                val pagerAdapter = com.example.mealbox1.MenuAdapter(this, msg)
                viewPager.adapter = pagerAdapter
        }
        initViews()
    }

    private fun initViews() {
        viewPager.setPageTransformer { page, position ->
            when {
                position.absoluteValue >= 1F -> {
                    page.alpha = 0F
                }
                position == 0F -> {
                    page.alpha = 1F
                }
                else -> {
                    page.alpha = 1F - 2 * position.absoluteValue
                }
            }
        }
    }
    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

}
