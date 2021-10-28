package br.com.venday.sale.registerorder.domain

import java.math.BigDecimal

class OrderItem(
    val product: Product,
    val amount: Long,
    val discount: BigDecimal,
    val price: BigDecimal,
    val total: BigDecimal
)
