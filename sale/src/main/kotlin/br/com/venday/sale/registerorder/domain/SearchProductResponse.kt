package br.com.venday.sale.registerorder.domain

import java.math.BigDecimal

data class SearchProductResponse(
    val id: String,
    val description: String,
    val price: BigDecimal
)
