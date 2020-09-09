
package com.zenith.stayfit.ui.diary.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.zenith.stayfit.databinding.ItemFoodBinding
import com.zenith.stayfit.ui.diary.model.Result
import java.util.Locale
import kotlin.collections.ArrayList

class AllFoodsRecyclerViewAdapter(var foodItems: List<Result>) :
    RecyclerView.Adapter<AllFoodsRecyclerViewAdapter.ViewHolder>(),
    Filterable {

    private lateinit var itemFoodBinding: ItemFoodBinding
    private var valueFilter: ValueFilter? = null
    var mStringFilterList: List<Result>? = foodItems
    var foodItemsCount: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        itemFoodBinding = ItemFoodBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemFoodBinding)
    }

    override fun getItemCount(): Int {
        return foodItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(foodItems[position])
    }

    class ViewHolder(private val itemFoodBinding: ItemFoodBinding) :
        RecyclerView.ViewHolder(itemFoodBinding.root) {
        fun bindItems(result: Result) {
            itemFoodBinding.tvFoodName.text = result.name
            itemFoodBinding.tvFoodCalories.text = result.energy.toString()
            itemFoodBinding.tvFoodQuantity.text = result.status
        }
    }

    override fun getFilter(): Filter {
        if (valueFilter == null) {
            valueFilter = ValueFilter()
        }
        return valueFilter as ValueFilter
    }

    inner class ValueFilter : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val results = FilterResults()
            if (constraint.isNotEmpty()) {
                val filterList: MutableList<Result> = ArrayList()
                for (i in mStringFilterList?.indices!!) {
                    if (mStringFilterList!![i].name.toUpperCase(Locale.ROOT).contains(
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
            foodItemsCount = results.count
            foodItems = results.values as List<Result>
            notifyDataSetChanged()
        }
    }
}
