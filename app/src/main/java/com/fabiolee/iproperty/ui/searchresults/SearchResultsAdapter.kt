package com.fabiolee.iproperty.ui.searchresults

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fabiolee.iproperty.R
import com.fabiolee.iproperty.databinding.SearchResultsItemBinding
import com.fabiolee.iproperty.repository.model.SearchResults

class SearchResultsAdapter(
    private var listener: (propertyId: String?) -> Unit
) : RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    private var data: List<SearchResults>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchResultsItemBinding.inflate(
            inflater, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (data.isNullOrEmpty()) {
            return
        }
        val item = data!![position]
        holder.binding.message.apply {
            text = context.getString(R.string.label_title_param, item.title)
        }
        holder.binding.button.apply {
            text = context.getString(R.string.label_property_id_param, item.id)
            setOnClickListener { listener.invoke(item.id) }
        }
    }

    override fun getItemCount(): Int {
        return if (data.isNullOrEmpty()) {
            0
        } else {
            data!!.size
        }
    }

    fun updateData(data: List<SearchResults>?) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        val binding: SearchResultsItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

}