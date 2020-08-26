
package com.zenith.stayfit.ui.diary

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zenith.stayfit.R
import com.zenith.stayfit.databinding.FragmentDiaryBinding
import com.zenith.stayfit.ui.diary.data.local.DiaryItems
import com.zenith.stayfit.ui.diary.view.adapters.CustomRecyclerViewItemTouchListener
import com.zenith.stayfit.ui.diary.view.adapters.DiaryAdapter

class DiaryFragment : Fragment(R.layout.fragment_diary) {

    private lateinit var fragmentDiaryBinding: FragmentDiaryBinding

    private val diaryAdapter: DiaryAdapter by lazy {
        DiaryAdapter(DiaryItems.getItems())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentDiaryBinding = FragmentDiaryBinding.bind(view)
        setupRecyclerView(fragmentDiaryBinding.rvDiary)
    }

    private fun setupRecyclerView(@NonNull recyclerView: RecyclerView) {
        recyclerView.adapter = diaryAdapter
        (recyclerView.adapter as DiaryAdapter) {
            DiaryItems.getItems()
        }
        recyclerView.addOnItemTouchListener(
            CustomRecyclerViewItemTouchListener(recyclerView,
            intArrayOf(R.id.btn_add),
            object : CustomRecyclerViewItemTouchListener.MyCustomClickListener {
                override fun onAddClick(view: View, position: Int) {
                    Toast.makeText(requireContext(), "add button clicked", Toast.LENGTH_SHORT).show()
                }

                override fun onClick(view: View, position: Int) {
                    // unused
                }
                override fun onLongClick(view: View, position: Int) {
                    // unused
                }
            })
        )
    }
}
