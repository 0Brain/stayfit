package com.zenith.stayfit.ui.diary.view.fragments

import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.lapism.search.internal.SearchLayout
import com.lapism.search.util.SearchUtils
import com.zenith.stayfit.R
import com.zenith.stayfit.databinding.FragmentAllBinding
import com.zenith.stayfit.ui.diary.model.Food
import com.zenith.stayfit.ui.diary.network.FoodService
import com.zenith.stayfit.ui.diary.view.adapters.AllFoodsRecyclerViewAdapter
import com.zenith.stayfit.ui.diary.view.viewmodel.AllViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class AllFragment : Fragment(R.layout.fragment_all) {

    private lateinit var fragmentAllBinding: FragmentAllBinding
    private lateinit var adapter: AllFoodsRecyclerViewAdapter
    private val allViewModel: AllViewModel by viewModels()
    @Inject
    lateinit var foodService: FoodService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentAllBinding = FragmentAllBinding.bind(view)

        lifecycleScope.launch {
            allViewModel.getFoodItems().collect { results ->
                Timber.d("Thread in viewmodel = ${Thread.currentThread().name}")
                adapter = AllFoodsRecyclerViewAdapter(results)
                fragmentAllBinding.svAll.apply {
                    navigationIconSupport = SearchLayout.NavigationIconSupport.SEARCH
                    setOnNavigationClickListener(object : SearchLayout.OnNavigationClickListener {
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
                            adapter.filter.filter(query)
                            fragmentAllBinding.tvCount.text = adapter.foodItemsCount.toString()
                            fragmentAllBinding.rvAll.adapter = adapter
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
    }
}