package com.fabiolee.iproperty.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:src")
fun ImageView.setImageUrl(url: String?) {
    Glide.with(context).load(url).into(this)
}