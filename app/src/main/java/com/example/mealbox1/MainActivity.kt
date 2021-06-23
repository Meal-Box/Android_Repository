package com.example.mealbox1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private val viewPager: ViewPager2 by lazy {
        findViewById(R.id.mealTextView)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

                }

    private fun initViews(){
        viewPager.adapter = menuAdapter(
            listOf(
                Meal(
                    "오늘의 급식",
                    "쌀밥, 미역국, 청경채"
                )
            )

        )
    }
}