package br.com.venday.sale.registerorder.domain

import java.math.BigDecimal
import java.time.Instant

data class GenerateBillRequest(
    val orderId: String,
    val createdAt: Instant,
    val total: BigDecimal
)
