package com.zenith.stayfit.ui.meals.view.adapters

import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zenith.stayfit.R
import com.zenith.stayfit.databinding.ItemMealBinding
import com.zenith.stayfit.ui.meals.model.MealType

class MealTypeAdapter : RecyclerView.Adapter<MealTypeAdapter.ViewHolder>() {

    private var meals: List<MealType> = ArrayList()
    private var globalPosition: Int? = null
    private lateinit var binding: ItemMealBinding
    var onItemClick: ((MealType) -> Unit)? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemMealBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resource: Resources = holder.itemView.context.resources
        holder.bindItems(meals[position])
        binding.apply {
            if (position == globalPosition) {
                tvMeal.setTextColor(resource.getColor(R.color.colorPrimary, null))
                cvMeals.strokeColor = resource.getColor(R.color.colorPrimary, null)
            } else {
                cvMeals.strokeColor = resource.getColor(R.color.tvBasic, null)
                tvMeal.setTextColor(resource.getColor(R.color.tvBasic, null))
            }
        }
    }

    fun insertTo(meals: List<MealType>) {
        this.meals = meals
    }


    @RequiresApi(Build.VERSION_CODES.M)
    inner class ViewHolder(private var binding: ItemMealBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.cvMeals.setOnClickListener {
                onItemClick?.invoke(meals[adapterPosition])
                globalPosition = adapterPosition
                notifyItemChanged(globalPosition!!)
            }
        }

        fun bindItems(mealType: MealType) {
            binding.apply {
                Glide.with(itemView).load(mealType.image)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(imMeal)
                tvMeal.text = mealType.name
            }
        }
    }
}