package br.com.venday.sale.shared.domain

import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class Product(
    @field:NotBlank
    val id: String,

    @field:NotBlank
    val description: String,

    @field:NotNull
    @field:Positive
    val price: BigDecimal,
) {

}
