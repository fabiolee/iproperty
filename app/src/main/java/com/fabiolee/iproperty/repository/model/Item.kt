package com.fabiolee.iproperty.repository.model

data class Item(
    val id: String? = null,
    val title: String? = null,
    val propertyType: String? = null,
    val prices: List<Price>? = null,
    val cover: Media? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val address: Address? = null,
    val featureDescription: String? = null,
    val attributes: Attributes? = null,
    val listers: List<Lister>? = null
) {

    interface Listener {
        fun onClickCall(phones: List<Phone>?) {
        }

        fun onClickContainer(propertyId: String?) {
        }

        fun onClickSms(phones: List<Phone>?) {
        }
    }

}