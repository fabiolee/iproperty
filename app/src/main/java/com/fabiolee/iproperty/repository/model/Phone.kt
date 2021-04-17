package com.fabiolee.iproperty.repository.model

data class Phone(val label: String? = null, val number: String? = null) {

    companion object {
        const val LABEL_MOBILE = "Mobile"
        const val LABEL_WHATSAPP = "whatsapp"
    }

}