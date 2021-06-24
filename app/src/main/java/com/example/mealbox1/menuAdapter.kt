package com.example.mealbox1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter



class menuAdapter(
    private val meal:List<Meal>,
    private val isNameRevealed:Boolean
): RecyclerView.Adapter<menuAdapter.menuViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        menuViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_main, parent, false)
        )

    override fun onBindViewHolder(holder: menuViewHolder, position: Int) {
        val actualPosition = position % meal.size
        holder.bind(meal[position], isNameRevealed)
    }

    override fun getItemCount() = Int.MAX_VALUE

    class menuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mealTextView: TextView = itemView.findViewById(R.id.mealTextView)


        @SuppressLint("SetTextI18n")
        fun bind(quote: Meal, isNameRevealed: Boolean) {
            mealTextView.text = "\"${quote.meal}\""


        }
    }
}
