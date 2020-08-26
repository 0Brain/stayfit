
package com.zenith.stayfit.ui.meals.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.zenith.stayfit.R

class MealsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meals)
        val toolbar: Toolbar = findViewById(R.id.meals_toolbar)
        setSupportActionBar(toolbar)
    }
}
