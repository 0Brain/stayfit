
package com.zenith.stayfit

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zenith.stayfit.databinding.ActivityMainBinding
import com.zenith.stayfit.ui.meals.view.MealsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // Lazy initialization of animation objects fot FAB
    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim)
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim)
    }
    private val fromBottomLeft: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.from_bottom_left_anim)
    }
    private val toBottomLeft: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.to_bottom_left_anim)
    }
    private val fromBottomRight: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.from_bottom_right_anim)
    }
    private val toBottomRight: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.to_bottom_right_anim)
    }
    private val fromBottomToTop: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.from_bottom_to_top)
    }
    private val toBottomFromTop: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.to_bottom_from_top)
    }
    private var clicked = false
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_diary,
                R.id.navigation_workout,
                R.id.navigation_nutrition,
                R.id.navigation_user
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.apply {
            fabMain.setOnClickListener {
                onAddButtonClicked()
            }
            fabWater.setOnClickListener {
                Toast.makeText(this@MainActivity, "Button Clicked 1", Toast.LENGTH_SHORT).show()
            }
            fabWorkout.setOnClickListener {
                Toast.makeText(this@MainActivity, "Button Clicked 2", Toast.LENGTH_SHORT).show()
            }
            fabMeals.setOnClickListener {
                Intent(this@MainActivity, MealsActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {
        binding.apply {
            if (!clicked) {
                fabWater.visibility = View.VISIBLE
                fabWorkout.visibility = View.VISIBLE
                fabMeals.visibility = View.VISIBLE
            } else {
                fabWater.visibility = View.INVISIBLE
                fabWorkout.visibility = View.VISIBLE
                fabMeals.visibility = View.VISIBLE
            }
        }
    }

    private fun setAnimation(clicked: Boolean) {
        binding.apply {
            if (!clicked) {
                fabWater.startAnimation(fromBottomToTop)
                fabWorkout.startAnimation(fromBottomLeft)
                fabMeals.startAnimation(fromBottomRight)
                fabMain.startAnimation(rotateOpen)
            } else {
                fabWater.startAnimation(toBottomFromTop)
                fabWorkout.startAnimation(toBottomLeft)
                fabMeals.startAnimation(toBottomRight)
                fabMain.startAnimation(rotateClose)
            }
        }
    }

    private fun setClickable(clicked: Boolean) {
        binding.apply {
            fabWater.isClickable = !clicked
            fabWorkout.isClickable = !clicked
            fabMeals.isClickable = !clicked
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}
