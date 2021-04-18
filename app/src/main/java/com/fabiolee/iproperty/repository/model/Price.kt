package com.fabiolee.iproperty.repository.model

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

data class Price(val currency: String? = null, val max: Long = 0, val min: Long = 0)

fun Price.formatMoney(): String {
    return "${getCurrencySymbol()} ${getCurrencyAmount(BigDecimal.valueOf(min))}"
}

fun Price.formatInstallment(): String {
    val installment = BigDecimal.valueOf(min)
        .multiply(BigDecimal.valueOf(4.7))
        .divide(BigDecimal.valueOf(100))
        .divide(BigDecimal.TEN)
    return "${getCurrencySymbol()} ${getCurrencyAmount(installment)}"
}

private fun getCurrencyAmount(amount: BigDecimal): String? {
    val numberFormatter = NumberFormat.getInstance()
    numberFormatter.isGroupingUsed = true
    return numberFormatter.format(amount)
}

private fun Price.getCurrencySymbol(): String? {
    val locale = Locale("ms", "MY")
    val currencyFormatter: NumberFormat = NumberFormat.getCurrencyInstance(locale)
    currencyFormatter.currency = Currency.getInstance(currency)
    return currencyFormatter.currency?.getSymbol(locale)
}