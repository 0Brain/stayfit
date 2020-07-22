
package com.zenith.stayfit.ui.supplements

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zenith.stayfit.R
import com.zenith.stayfit.ui.supplements.Adapter.SupplementAdapter
import com.zenith.stayfit.ui.supplements.model.Supplement
import com.zenith.stayfit.ui.supplements.viewmodels.SupplementsViewModel
import kotlinx.android.synthetic.main.fragment_supplements.*

class SupplementsFragment : Fragment(R.layout.fragment_supplements) {
    private lateinit var supplementsViewModel: SupplementsViewModel
    private var adapter = SupplementAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        supplementsViewModel = ViewModelProvider(this).get(SupplementsViewModel::class.java)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter

//        supplementsViewModel.getSupplements().observe(viewLifecycleOwner, Observer<List<Supplement>> {t->
//            adapter.setNotes(t!!)
//        })
    }

    fun supplementClicked(supplement: Supplement) {
        val supplementDetailIntent = Intent(context, SupplementDetail::class.java)
        startActivity(supplementDetailIntent)
    }
}
