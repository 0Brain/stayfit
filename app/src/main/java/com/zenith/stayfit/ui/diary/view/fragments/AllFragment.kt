
package com.zenith.stayfit.ui.diary.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.lapism.search.internal.SearchLayout
import com.lapism.search.util.SearchUtils
import com.zenith.stayfit.R
import com.zenith.stayfit.databinding.FragmentAllBinding
import com.zenith.stayfit.ui.diary.view.adapters.AllFoodsRecyclerViewAdapter
import com.zenith.stayfit.ui.diary.view.viewmodel.AllViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllFragment: Fragment(R.layout.fragment_all) {

    private lateinit var fragmentAllBinding: FragmentAllBinding
    private lateinit var adapter: AllFoodsRecyclerViewAdapter
    private val allViewModel: AllViewModel by viewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentAllBinding = FragmentAllBinding.bind(view)

        fragmentAllBinding.svAll.apply {
            navigationIconSupport = SearchLayout.NavigationIconSupport.SEARCH
            setOnNavigationClickListener(object :
                SearchLayout.OnNavigationClickListener {
                override fun onNavigationClick() {
                    requestFocus()
                }
            })
            setTextHint(getString(R.string.search))
            setOnQueryTextListener(object : SearchLayout.OnQueryTextListener {
                override fun onQueryTextChange(newText: CharSequence): Boolean {
                    return false
                }

                override fun onQueryTextSubmit(query: CharSequence): Boolean {
                    val time = measureTimeMillis {
                        lifecycleScope.launch {
                            allViewModel.getFoodItems().collect { results ->
                                adapter = AllFoodsRecyclerViewAdapter(results)
                                adapter.filter.filter(query)
                                fragmentAllBinding.tvCount.text =
                                    adapter.foodItemsCount.toString()
                                fragmentAllBinding.rvAll.adapter = adapter
                            }
                        }
                    }
                    Toast.makeText(requireContext(), "$time", Toast.LENGTH_LONG).show()
                    return true
                }
            })
            setMicIconImageResource(R.drawable.ic_mic)
            setOnMicClickListener(object : SearchLayout.OnMicClickListener {
                override fun onMicClick() {
                    if (activity?.let { SearchUtils.isVoiceSearchAvailable(it) }!!) {
                        activity?.let {
                            SearchUtils.setVoiceSearch(
                                it,
                                getString(R.string.speak)
                            )
                        }
                    }
                }
            })
            setMenuIconImageResource(R.drawable.ic_barcode)
            setOnMenuClickListener(object : SearchLayout.OnMenuClickListener {
                override fun onMenuClick() {
                }
            })

            elevation = 0f
            setBackgroundStrokeWidth(resources.getDimensionPixelSize(R.dimen.search_stroke_width))
            setBackgroundStrokeColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.AshGray
                )
            )
            setOnFocusChangeListener(object : SearchLayout.OnFocusChangeListener {
                override fun onFocusChange(hasFocus: Boolean) {
                    navigationIconSupport = if (hasFocus) {
                        SearchLayout.NavigationIconSupport.ARROW
                    } else {
                        SearchLayout.NavigationIconSupport.SEARCH
                    }
                }
            })
        }
    }
}
