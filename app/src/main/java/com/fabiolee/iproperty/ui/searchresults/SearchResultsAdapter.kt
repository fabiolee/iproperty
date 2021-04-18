package com.fabiolee.iproperty.ui.searchresults

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fabiolee.iproperty.databinding.PropertyItemBinding
import com.fabiolee.iproperty.repository.model.Item

class SearchResultsAdapter(
    private var modelListener: Item.Listener
) : RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    private var data: List<Item>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PropertyItemBinding.inflate(
            inflater, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (data.isNullOrEmpty()) {
            return
        }
        val item = data!![position]
        holder.binding.model = item
        holder.binding.modelListener = modelListener
    }

    override fun getItemCount(): Int {
        return if (data.isNullOrEmpty()) {
            0
        } else {
            data!!.size
        }
    }

    fun updateData(data: List<Item>?) {
        val previousItemCount = itemCount
        this.data = data
        val currentItemCount = itemCount
        when {
            previousItemCount == 0 -> {
                notifyItemRangeInserted(0, currentItemCount)
            }
            currentItemCount == 0 -> {
                notifyItemRangeRemoved(0, previousItemCount)
            }
            else -> {
                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolder(
        val binding: PropertyItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

}