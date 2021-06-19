package com.example.mealbox1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    //  viewpager
    private val adapter by lazy { menuAdapterclass(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  initialize viewpager
        .adapter = MainActivity@adapter

                }

            }