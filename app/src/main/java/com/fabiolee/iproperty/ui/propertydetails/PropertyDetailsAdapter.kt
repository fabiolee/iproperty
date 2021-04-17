package com.fabiolee.iproperty.ui.propertydetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fabiolee.iproperty.databinding.PropertyItemBinding
import com.fabiolee.iproperty.repository.model.*

class PropertyDetailsAdapter : RecyclerView.Adapter<PropertyDetailsAdapter.ViewHolder>() {

    private var data: PropertyDetailsResponse? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PropertyItemBinding.inflate(
            inflater, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (data == null) {
            return
        }
        val item = Item(
            title = data?.title,
            propertyType = data?.propertyType,
            prices = data?.prices,
            cover = data?.cover,
            publishedAt = data?.publishedAt,
            address = data?.address,
            attributes = data?.attributes
        )
        holder.binding.model = item
    }

    override fun getItemCount(): Int {
        return if (data == null) {
            0
        } else {
            1
        }
    }

    fun updateData(data: PropertyDetailsResponse?) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        val binding: PropertyItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

}