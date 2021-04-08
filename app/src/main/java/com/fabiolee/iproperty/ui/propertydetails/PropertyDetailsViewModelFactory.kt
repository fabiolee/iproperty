package com.fabiolee.iproperty.ui.propertydetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PropertyDetailsViewModelFactory(private val propertyId: String?) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PropertyDetailsViewModel(propertyId) as T
    }

}