package com.fabiolee.iproperty.repository.model

data class Item(
    val id: String? = null,
    val title: String? = null,
    val propertyType: String? = null,
    val prices: List<Price>? = null,
    val cover: Media? = null,
    val publishedAt: String? = null,
    val address: Address? = null,
    val attributes: Attributes? = null,
    val listers: List<Lister>? = null
) {

    interface Listener {
        fun onClickContainer(propertyId: String?)
    }

}