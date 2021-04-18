package com.fabiolee.iproperty.ui

import android.view.View
import androidx.databinding.BindingAdapter
import com.fabiolee.iproperty.repository.model.Item
import com.fabiolee.iproperty.repository.model.Phone

@BindingAdapter("modelListener", "call")
fun View.onClickCall(modelListener: Item.Listener?, phones: List<Phone>?) {
    if (modelListener == null) {
        isEnabled = false
        setOnClickListener(null)
    } else {
        isEnabled = true
        setOnClickListener { modelListener.onClickCall(phones) }
    }
}

@BindingAdapter("modelListener", "propertyId")
fun View.onClickContainer(modelListener: Item.Listener?, propertyId: String?) {
    if (modelListener == null) {
        isEnabled = false
        setOnClickListener(null)
    } else {
        isEnabled = true
        setOnClickListener { modelListener.onClickContainer(propertyId) }
    }
}

@BindingAdapter("modelListener", "sms")
fun View.onClickSms(modelListener: Item.Listener?, phones: List<Phone>?) {
    if (modelListener == null) {
        isEnabled = false
        setOnClickListener(null)
    } else {
        isEnabled = true
        setOnClickListener { modelListener.onClickSms(phones) }
    }
}