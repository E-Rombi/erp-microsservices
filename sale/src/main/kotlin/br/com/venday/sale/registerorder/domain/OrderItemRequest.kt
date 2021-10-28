package br.com.venday.sale.registerorder.domain

import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class OrderItemRequest(
    @field:NotBlank
    val productId: String,

    @field:NotNull
    @field:Positive
    val price: BigDecimal,

    @field:Positive
    @field:NotNull
    val amount: Long,

    @field:Positive
    @field:NotNull
    val discount: BigDecimal
)
