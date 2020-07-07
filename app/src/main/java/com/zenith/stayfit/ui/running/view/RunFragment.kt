package com.zenith.stayfit.ui.running.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zenith.stayfit.MainActivity
import com.zenith.stayfit.R
import com.zenith.stayfit.ui.running.viewmodel.RunViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped


@AndroidEntryPoint
class RunFragment : Fragment(R.layout.fragment_run_setup) {

    private val slideshowViewModel: RunViewModel by lazy {
        ViewModelProvider(this).get(RunViewModel::class.java)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)!!.hideFloatingActionButton()
    }
}