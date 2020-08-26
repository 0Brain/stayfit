
package com.zenith.stayfit.ui.diary.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import com.zenith.stayfit.databinding.ItemDiaryBinding
import com.zenith.stayfit.ui.diary.model.Diary

class DiaryAdapter(diaryItems: MutableList<Diary>) : RecyclerView.Adapter<DiaryAdapter.ViewHolder>() {

    private var diaryItems: List<Diary> = diaryItems
    private lateinit var binding: ItemDiaryBinding

    @CallSuper
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemDiaryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    @CallSuper
    override fun getItemCount(): Int {
        return diaryItems.size
    }

    @CallSuper
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(diaryItems[position])
    }

    operator fun invoke(function: () -> MutableList<Diary>) {
    }

    inner class ViewHolder(private var binding: ItemDiaryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItems(diary: Diary) {
            binding.apply {
                tvActivityName.text = diary.activityName
                btnAdd.text = diary.addButton
            }
        }
    }
}
