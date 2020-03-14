package com.zenith.stayfit.ui.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.zenith.stayfit.R

class ProfileFragment() : Fragment(R.layout.profile_fragment) {

    companion object {
        fun newInstance() = ProfileFragment()
    }
    private lateinit var viewModel: ProfileViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
