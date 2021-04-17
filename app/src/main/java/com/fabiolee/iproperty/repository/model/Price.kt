package com.fabiolee.iproperty.repository.model

import java.text.NumberFormat
import java.util.*

data class Price(val currency: String? = null, val max: Int = 0, val min: Int = 0)

fun Price.formatMoney(): String {
    val locale = Locale("ms", "MY")
    val currencyFormatter: NumberFormat = NumberFormat.getCurrencyInstance(locale)
    currencyFormatter.currency = Currency.getInstance(currency)
    val symbol = currencyFormatter.currency?.getSymbol(locale)
    val numberFormatter = NumberFormat.getInstance()
    numberFormatter.isGroupingUsed = true
    return "$symbol ${numberFormatter.format(min)}"
}