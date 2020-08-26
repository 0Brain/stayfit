
package com.zenith.stayfit.ui.diary.view.adapters

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import com.zenith.stayfit.R
import com.zenith.stayfit.commons.BaseRecyclerViewItemTouchListener
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

open class CustomRecyclerViewItemTouchListener(
  @NotNull recycleView: RecyclerView,
  @Nullable @IdRes specialIds: IntArray?,
  @NotNull clickListener: MyCustomClickListener
) :
    BaseRecyclerViewItemTouchListener<CustomRecyclerViewItemTouchListener.MyCustomClickListener>(recycleView, specialIds, clickListener) {

    companion object {
        private const val SPECIAL_VIEW_CLICK_AREA_EXTENSION = 5 // this is gonna be converted to 5dp
    }

    private var clickPadding: Int

    init {
        clickPadding = (SPECIAL_VIEW_CLICK_AREA_EXTENSION * recycleView.resources.displayMetrics.density).toInt()
    }

    interface MyCustomClickListener : BaseRecyclerViewItemTouchListener.ClickListener {

        fun onAddClick(view: View, position: Int)
    }

    override fun onSpecialViewClick(
      @NotNull specialChildView: View,
      listPosition: Int
    ) {
        when (specialChildView.id) {
            R.id.btn_add -> mClickListener.onAddClick(specialChildView, listPosition)
            else -> mClickListener.onClick(specialChildView, listPosition)
        }
    }

    override fun getSpecialViewClickPadding(): Int = clickPadding
}
