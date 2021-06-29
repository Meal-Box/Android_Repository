package com.example.mealbox1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mealbox1.ui.fragment.breakfastActivity
import com.example.mealbox1.ui.fragment.dinnerActivity
import com.example.mealbox1.ui.fragment.lunchActivity


private const val NUM_PAGES = 3
class MenuAdapter(fa: FragmentActivity,val date: String) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = NUM_PAGES
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> breakfastActivity(date)
            1 -> lunchActivity(date)
            else -> dinnerActivity(date)
        }
    }

}