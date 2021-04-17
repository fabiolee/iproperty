package com.fabiolee.iproperty.ui.searchresults

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabiolee.iproperty.repository.model.Item
import com.fabiolee.iproperty.repository.model.SearchResultsResponse
import com.fabiolee.iproperty.repository.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultsViewModel(
    val searchText: String?,
    private val apiService: ApiService = ApiService.get()
) : ViewModel() {

    companion object {
        const val TAG = "SearchResultsViewModel"
    }

    val loading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val data: MutableLiveData<List<Item>?> by lazy {
        MutableLiveData<List<Item>?>().also {
            loadData(searchText)
        }
    }

    private fun loadData(searchText: String?) {
        loading.value = true
        apiService.getSearchResults(searchText).enqueue(object : Callback<SearchResultsResponse> {
            override fun onFailure(call: Call<SearchResultsResponse>, tr: Throwable) {
                Log.e(TAG, "Fail to get search results.", tr)
                loading.value = false
            }

            override fun onResponse(
                call: Call<SearchResultsResponse>,
                response: Response<SearchResultsResponse>
            ) {
                if (response.body() == null) {
                    data.postValue(null)
                } else {
                    data.postValue(response.body()!!.items)
                }
                loading.value = false
            }
        })
    }

}