
package com.zenith.stayfit.ui.profile.view.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zenith.stayfit.R

class ProfileMainFragment() : Fragment(R.layout.profile_fragment) {

    companion object {
        fun newInstance() =
            ProfileMainFragment()
    }
    private lateinit var viewModel: ProfileViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
    }
}
