package com.example.mealbox1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.annotation.UiThread

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashAnimation()
    }
    @UiThread
    private fun splashAnimation(){
        val imageView = findViewById<ImageView>(R.id.splash_imageView)
        val imageAnim : Animation = AnimationUtils.loadAnimation(this,R.anim.anim_splash_imageview)
        imageView.startAnimation(imageAnim)

        imageAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
            }

            override fun onAnimationEnd(animation: Animation) {
                val intent = Intent(this@Splash, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.anim_splash_out_top, R.anim.anim_splash_in_down)
                finish()
            }

            override fun onAnimationRepeat(animation: Animation) {
            }

        })
    }

}
