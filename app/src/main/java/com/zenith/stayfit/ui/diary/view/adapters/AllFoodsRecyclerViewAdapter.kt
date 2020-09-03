package com.zenith.stayfit.ui.diary.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zenith.stayfit.R
import com.zenith.stayfit.databinding.FragmentAllBinding
import com.zenith.stayfit.databinding.ItemFoodBinding
import com.zenith.stayfit.databinding.ItemMealBinding
import java.util.*
import kotlin.collections.ArrayList

class AllFoodsRecyclerViewAdapter :RecyclerView.Adapter<AllFoodsRecyclerViewAdapter.ViewHolder>(),
    Filterable {

    private lateinit var itemFoodBinding: ItemFoodBinding
    var mStringFilterList: List<String>? = null
    private var valueFilter: ValueFilter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        itemFoodBinding = ItemFoodBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemFoodBinding)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    class ViewHolder(itemFoodBinding: ItemFoodBinding) : RecyclerView.ViewHolder(itemFoodBinding.root){

    }

    override fun getFilter(): Filter {
        if(valueFilter == null){
            valueFilter = ValueFilter()
        }
        return valueFilter as ValueFilter
    }

    inner class ValueFilter : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val results = FilterResults()
            if (constraint.isNotEmpty()) {
                val filterList: MutableList<String> = ArrayList()
                for (i in mStringFilterList?.indices!!) {
                    if (mStringFilterList!![i].toUpperCase(Locale.ROOT).contains(
                            constraint.toString().toUpperCase(Locale.ROOT)
                        )
                    ) {
                        filterList.add(mStringFilterList!![i])
                    }
                }
                results.count = filterList.size
                results.values = filterList
            } else {
                results.count = mStringFilterList!!.size
                results.values = mStringFilterList
            }
            return results
        }

        override fun publishResults(
            constraint: CharSequence,
            results: FilterResults
        ) {
            //heroes = results.values as List<String>
            notifyDataSetChanged()
        }
    }
}