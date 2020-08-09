package com.zenith.stayfit.ui.appStarting

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.ramotion.paperonboarding.PaperOnboardingEngine
import com.ramotion.paperonboarding.PaperOnboardingPage
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnChangeListener
import com.zenith.stayfit.R
import com.zenith.stayfit.databinding.ActivityPaperOnBoardingBinding
import com.zenith.stayfit.ui.login.view.LoginActivity

class PaperOnBoardingActivity : AppCompatActivity() {

    lateinit var paperOnBoardingActivity: ActivityPaperOnBoardingBinding

    lateinit var engine: PaperOnboardingEngine

    var listSize: Int = 0

    lateinit var btnLoginAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        paperOnBoardingActivity = ActivityPaperOnBoardingBinding.inflate(layoutInflater)
        setContentView(paperOnBoardingActivity.root)

        setUpOnBoardingEngine()

        paperOnBoardingActivity.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    private fun setUpOnBoardingEngine() {

        engine = PaperOnboardingEngine(
            findViewById(R.id.onboardingRootView),
            getDataForOnboarding(),
            applicationContext
        )

        engine.setOnChangeListener(PaperOnboardingOnChangeListener { oldElementIndex, newElementIndex ->

            if (newElementIndex == listSize - 1) {
                paperOnBoardingActivity.btnLogin.visibility = View.VISIBLE
                btnLoginAnimation =
                    AnimationUtils.loadAnimation(this, R.anim.app_onboarding_button_animation)
                paperOnBoardingActivity.btnLogin.animation = btnLoginAnimation
            } else {
                paperOnBoardingActivity.btnLogin.visibility = View.GONE
            }
        })

    }

    private fun getDataForOnboarding(): ArrayList<PaperOnboardingPage> {
        val page1 = PaperOnboardingPage(
            "Board 1",
            "StayFit Application Board 1",
            Color.parseColor("#678FB4"),
            R.drawable.ic_person,
            R.drawable.onboarding_pager_round_icon
        )
        val page2 = PaperOnboardingPage(
            "Board 2",
            "StayFit Application Board 2",
            Color.parseColor("#65B0B4"),
            R.drawable.ic_person,
            R.drawable.onboarding_pager_round_icon
        )
        val page3 = PaperOnboardingPage(
            "Board 3",
            "StayFit Application Board 3",
            Color.parseColor("#9B90BC"),
            R.drawable.ic_person,
            R.drawable.onboarding_pager_round_icon
        )

        val elements: ArrayList<PaperOnboardingPage> = ArrayList()
        elements.add(page1)
        elements.add(page2)
        elements.add(page3)
        listSize = elements.size
        return elements
    }
}