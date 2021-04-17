package com.fabiolee.iproperty.repository.model

data class SearchResultsResponse(
    val totalCount: Int = 0,
    val nextPageToken: String? = null,
    val items: List<Item>? = null
)