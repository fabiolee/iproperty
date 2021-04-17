package com.fabiolee.iproperty.ui

import android.view.View
import androidx.databinding.BindingAdapter
import com.fabiolee.iproperty.repository.model.Item

@BindingAdapter("modelListener", "propertyId")
fun View.onClick(modelListener: Item.Listener?, propertyId: String?) {
    if (modelListener == null) {
        isEnabled = false
        setOnClickListener(null)
    } else {
        isEnabled = true
        setOnClickListener { modelListener.onClickContainer(propertyId) }
    }
}