
package com.zenith.stayfit.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zenith.stayfit.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var homeViewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            text_home.text = it.toString()
        })
    }
}
