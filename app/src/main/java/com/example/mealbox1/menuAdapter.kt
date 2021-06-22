package com.example.mealbox1

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

class menuAdapter(
    val meal:List<Meal>
): RecyclerView.Adapter<menuAdapter.menuViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        menuViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_menu,parent,false)
        )

    override fun onBindViewHolder(holder: menuViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = meal.size

    class menuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val mealTextView:TextureView=itemView.findViewById(R.id.mealTextView)
        private val nameTextView:TextureView=itemView.findViewById(R.id.nameTextView)


        fun bind(meal:Meal){
            mealTextView.text = meal.meal
            nameTextView.text = meal.name

        }

    }



}