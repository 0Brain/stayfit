
package com.zenith.stayfit.ui.appStarting

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.zenith.stayfit.R
import com.zenith.stayfit.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var splashScreenActivity: ActivitySplashScreenBinding

    private lateinit var appLogoAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashScreenActivity = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashScreenActivity.root)

        setGif()

        setAppTextAnimation()

        setNextScreen()
    }

    private fun setGif() {

        Glide.with(this)
            .load(R.drawable.splash_gif)
            .into(splashScreenActivity.ivGif)
    }

    private fun setAppTextAnimation() {

        appLogoAnimation = AnimationUtils.loadAnimation(this, R.anim.app_text_animation)

        splashScreenActivity.tvAppText.animation = appLogoAnimation
    }

    private fun setNextScreen() {

        Handler().postDelayed({
            // Start activity
            startActivity(Intent(this, PaperOnBoardingActivity::class.java))
            // Animate the loading of new activity
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            // Close this activity
            finish()
        }, 4000)
    }
}
