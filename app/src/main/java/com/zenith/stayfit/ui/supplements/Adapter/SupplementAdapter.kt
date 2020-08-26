
package com.zenith.stayfit.ui.supplements.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zenith.stayfit.R
import com.zenith.stayfit.ui.supplements.model.Supplement

class SupplementAdapter() : RecyclerView.Adapter<SupplementAdapter.ViewHolder>() {

    private var supplementList: List<Supplement> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.supplements_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return supplementList.size
    }

    fun setNotes(supplement: List<Supplement>) {
        this.supplementList = supplement
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(supplementList[position])
    }

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(supplementItem: Supplement) {
            val textViewName = itemView.findViewById(R.id.primary_supplement) as TextView
            val textViewSubtext = itemView.findViewById(R.id.supplement_subtext) as TextView
            val textViewDescription = itemView.findViewById(R.id.supplement_description) as TextView
            val expandButton = itemView.findViewById(R.id.expand_button) as ImageButton
            textViewName.text = supplementItem.title
            textViewSubtext.text = supplementItem.subText
            textViewDescription.text = supplementItem.description
            textViewDescription.visibility = View.GONE
            expandButton.setImageResource(R.drawable.ic_expand_more_black_36dp)

            expandButton.setOnClickListener {
                if (textViewDescription.visibility == View.GONE) {
                    textViewDescription.visibility = View.VISIBLE
                    expandButton.setImageResource(R.drawable.ic_expand_less_black_36dp)
                } else if (textViewDescription.visibility == View.VISIBLE) {
                    textViewDescription.visibility = View.GONE
                    expandButton.setImageResource(R.drawable.ic_expand_more_black_36dp)
                }
            }
        }
    }
}
