package com.zenith.stayfit

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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val rotateOpen:Animation by lazy {
        AnimationUtils.loadAnimation(this,R.anim.rotate_open_anim)
    }
    private val rotateClose:Animation by lazy {
        AnimationUtils.loadAnimation(this,R.anim.rotate_close_anim)
    }
    private val fromBottomLeft:Animation by lazy {
        AnimationUtils.loadAnimation(this,R.anim.from_bottom_left_anim)
    }
    private val toBottomLeft:Animation by lazy {
        AnimationUtils.loadAnimation(this,R.anim.to_bottom_left_anim)
    }
    private val fromBottomRight:Animation by lazy {
        AnimationUtils.loadAnimation(this,R.anim.from_bottom_right_anim)
    }
    private val toBottomRight:Animation by lazy {
        AnimationUtils.loadAnimation(this,R.anim.to_bottom_right_anim)
    }
    private val fromBottomToTop:Animation by lazy {
        AnimationUtils.loadAnimation(this,R.anim.from_bottom_to_top)
    }
    private val toBottomFromTop:Animation by lazy {
        AnimationUtils.loadAnimation(this,R.anim.to_bottom_from_top)
    }
    private var clicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

        floating_action_button.setOnClickListener {
            onAddButtonClicked()
        }
        floatingActionButton5.setOnClickListener {
            Toast.makeText(this, "Button Clicked 1", Toast.LENGTH_SHORT).show()
        }
        floatingActionButton6.setOnClickListener {
            Toast.makeText(this, "Button Clicked 2", Toast.LENGTH_SHORT).show()
        }
        floatingActionButton7.setOnClickListener {
            Toast.makeText(this, "Button Clicked 3", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked:Boolean) {
        if(!clicked){
            floatingActionButton5.visibility = View.VISIBLE
            floatingActionButton6.visibility = View.VISIBLE
            floatingActionButton7.visibility = View.VISIBLE
        }else{
            floatingActionButton5.visibility = View.INVISIBLE
            floatingActionButton6.visibility = View.VISIBLE
            floatingActionButton7.visibility = View.VISIBLE
        }
    }

    private fun setAnimation(clicked:Boolean) {
        if(!clicked){
            floatingActionButton5.startAnimation(fromBottomToTop)
            floatingActionButton6.startAnimation(fromBottomLeft)
            floatingActionButton7.startAnimation(fromBottomRight)
            floating_action_button.startAnimation(rotateOpen)
        }else{
            floatingActionButton5.startAnimation(toBottomFromTop)
            floatingActionButton6.startAnimation(toBottomLeft)
            floatingActionButton7.startAnimation(toBottomRight)
            floating_action_button.startAnimation(rotateClose)
        }
    }

    private fun setClickable(clicked:Boolean){
        floatingActionButton5.isClickable = !clicked
        floatingActionButton6.isClickable = !clicked
        floatingActionButton7.isClickable = !clicked
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}
