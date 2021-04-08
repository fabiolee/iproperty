package com.fabiolee.iproperty.repository.remote

import com.fabiolee.iproperty.repository.model.PropertyDetailsResponse
import com.fabiolee.iproperty.repository.model.SearchResultsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.fastjson.FastJsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val PARAMETER_ALT = "alt"
        const val PARAMETER_PROPERTY_ID = "propertyId"
        const val PARAMETER_SEARCH_TEXT = "searchText"
        const val PARAMETER_TOKEN = "token"

        fun get(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://firebasestorage.googleapis.com/v0/b/fir-quickstart-8bea9.appspot.com/o/")
                .addConverterFactory(FastJsonConverterFactory.create())
                .build()
            return retrofit.create<ApiService>(ApiService::class.java)
        }
    }

    @GET("rea%2Fsearch-results-page.json")
    fun getSearchResults(
        @Query(PARAMETER_SEARCH_TEXT) searchText: String?,
        @Query(PARAMETER_ALT) alt: String = "media",
        @Query(PARAMETER_TOKEN) token: String = "c45a9e91-7eca-4294-b441-d957e599037c"
    ): Call<SearchResultsResponse>

    @GET("rea%2Fproperty-details-page.json")
    fun getPropertyDetails(
        @Query(PARAMETER_PROPERTY_ID) propertyId: String?,
        @Query(PARAMETER_ALT) alt: String = "media",
        @Query(PARAMETER_TOKEN) token: String = "a4de4486-ab8e-4dab-bb4b-225a21b47a67"
    ): Call<PropertyDetailsResponse>

}