package com.fabiolee.iproperty.ui.propertydetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.fabiolee.iproperty.BR
import com.fabiolee.iproperty.R
import com.fabiolee.iproperty.repository.model.Item
import java.lang.IllegalArgumentException

class PropertyDetailsAdapter(
    private var modelListener: Item.Listener
) : RecyclerView.Adapter<PropertyDetailsAdapter.BindingViewHolder>() {

    private var data: List<Item>? = null

    companion object {
        const val VIEW_TYPE_PROPERTY = 1
        const val VIEW_TYPE_CALCULATOR = 2
        const val VIEW_TYPE_DESCRIPTION = 3
        const val VIEW_TYPE_PROPERTY_DETAIL = 4
        const val VIEW_TYPE_FACILITY = 5
        const val VIEW_TYPE_LISTER = 6
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layoutId = getLayoutId(viewType)
        return BindingViewHolder(DataBindingUtil.inflate(inflater, layoutId, parent, false))
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        if (data.isNullOrEmpty()) {
            return
        }
        val item = data!![position]
        holder.binding.setVariable(BR.model, item)
        holder.binding.setVariable(BR.modelListener, modelListener)
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

    fun updateData(data: List<Item>) {
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

    private fun getLayoutId(viewType: Int): Int {
        return when (viewType) {
            VIEW_TYPE_PROPERTY -> R.layout.property_item
            VIEW_TYPE_CALCULATOR -> R.layout.calculator_item
            VIEW_TYPE_DESCRIPTION -> R.layout.description_item
            VIEW_TYPE_PROPERTY_DETAIL -> R.layout.property_detail_item
            VIEW_TYPE_FACILITY -> R.layout.facility_item
            VIEW_TYPE_LISTER -> R.layout.lister_item
            else -> throw IllegalArgumentException("Invalid viewType.")
        }
    }

    inner class BindingViewHolder(
        val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root)

}