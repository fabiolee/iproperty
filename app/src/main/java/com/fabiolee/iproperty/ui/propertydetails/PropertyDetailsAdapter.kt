package com.fabiolee.iproperty.ui.propertydetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fabiolee.iproperty.databinding.CalculatorItemBinding
import com.fabiolee.iproperty.databinding.DescriptionItemBinding
import com.fabiolee.iproperty.databinding.PropertyItemBinding
import com.fabiolee.iproperty.repository.model.*
import java.lang.IllegalArgumentException

class PropertyDetailsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data: List<Any?>? = null

    companion object {
        const val VIEW_TYPE_PROPERTY = 1
        const val VIEW_TYPE_CALCULATOR = 2
        const val VIEW_TYPE_DESCRIPTION = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_PROPERTY -> {
                PropertyViewHolder(PropertyItemBinding.inflate(inflater, parent, false))
            }
            VIEW_TYPE_CALCULATOR -> {
                CalculatorViewHolder(CalculatorItemBinding.inflate(inflater, parent, false))
            }
            VIEW_TYPE_DESCRIPTION -> {
                DescriptionViewHolder(DescriptionItemBinding.inflate(inflater, parent, false))
            }
            else -> {
                throw IllegalArgumentException("Invalid viewType.")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (data.isNullOrEmpty()) {
            return
        }
        val item = data!![position]
        when (holder) {
            is PropertyViewHolder -> {
                holder.binding.model = item as Item?
            }
            is CalculatorViewHolder -> {
                holder.binding.model = item as Price?
            }
            is DescriptionViewHolder -> {
                holder.binding.model = item as Item?
            }
        }
    }

    override fun getItemCount(): Int {
        return if (data.isNullOrEmpty()) {
            0
        } else {
            data!!.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position + 1
    }

    fun updateData(data: List<Any?>) {
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

    inner class PropertyViewHolder(
        val binding: PropertyItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    inner class CalculatorViewHolder(
        val binding: CalculatorItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    inner class DescriptionViewHolder(
        val binding: DescriptionItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

}