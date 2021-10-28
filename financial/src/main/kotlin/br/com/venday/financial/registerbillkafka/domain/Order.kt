package br.com.venday.financial.registerbillkafka.domain

import java.math.BigDecimal
import java.time.Instant

class Order(
    val id: String,
    val createdAt: Instant,
    val total: BigDecimal
)
