package com.zenith.stayfit.ui.supplements

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zenith.stayfit.MainActivity
import com.zenith.stayfit.R
import com.zenith.stayfit.ui.supplements.Adapter.SupplementAdapter
import com.zenith.stayfit.ui.supplements.model.Supplement
import com.zenith.stayfit.ui.supplements.viewmodel.SupplementsViewModel
import kotlinx.android.synthetic.main.fragment_supplements.*


class SupplementsFragment : Fragment(R.layout.fragment_supplements) {
    private lateinit var supplementsViewModel: SupplementsViewModel
    private val adapter:SupplementAdapter by lazy {
        SupplementAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        supplementsViewModel = ViewModelProvider(this).get(SupplementsViewModel::class.java)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter

        (activity as MainActivity?)!!.hideFloatingActionButton()
        supplementsViewModel.getSupplements().observe(viewLifecycleOwner, Observer{t->
            adapter.setNotes(t!!)
        })
    }

    fun supplementClicked(supplement: Supplement){
        val supplementDetailIntent = Intent(context,SupplementDetail::class.java)
        startActivity(supplementDetailIntent)
    }
}