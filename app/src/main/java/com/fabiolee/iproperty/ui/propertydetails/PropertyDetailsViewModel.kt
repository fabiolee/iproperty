package com.fabiolee.iproperty.ui.propertydetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabiolee.iproperty.repository.model.PropertyDetailsResponse
import com.fabiolee.iproperty.repository.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PropertyDetailsViewModel(
    val propertyId: String?,
    private val apiService: ApiService = ApiService.get()
) : ViewModel() {

    companion object {
        const val TAG = "PropertyDetailsVm"
    }

    val loading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val data: MutableLiveData<String> by lazy {
        MutableLiveData<String>().also {
            loadData(propertyId)
        }
    }

    private fun loadData(propertyId: String?) {
        loading.value = true
        apiService.getPropertyDetails(propertyId)
            .enqueue(object : Callback<PropertyDetailsResponse> {
                override fun onFailure(call: Call<PropertyDetailsResponse>, tr: Throwable) {
                    Log.e(TAG, "Fail to get property details.", tr)
                    loading.value = false
                }

                override fun onResponse(
                    call: Call<PropertyDetailsResponse>,
                    response: Response<PropertyDetailsResponse>
                ) {
                    data.postValue("propertyId=${propertyId}\ntitle=${response.body()?.title}")
                    loading.value = false
                }
            })
    }

}