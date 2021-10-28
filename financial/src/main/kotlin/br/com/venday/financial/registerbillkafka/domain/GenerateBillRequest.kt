package br.com.venday.financial.registerbillkafka.domain

import java.math.BigDecimal
import java.time.Instant

data class GenerateBillRequest(
    val orderId: String,
    val createdAt: Instant,
    val total: BigDecimal
) {
    fun toBill(): Bill {
        return Bill(Order(this.orderId, this.createdAt, this.total), this.total)
    }
}
