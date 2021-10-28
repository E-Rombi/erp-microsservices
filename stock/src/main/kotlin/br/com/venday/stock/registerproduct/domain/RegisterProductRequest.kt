package br.com.venday.stock.registerproduct.domain

import br.com.venday.stock.shared.domain.Product
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

data class RegisterProductRequest(
    @field:NotBlank
    @field:Size(min = 10, max = 200)
    val description: String?,

    @field:NotNull
    @field:Positive
    val price: BigDecimal?
) {
    fun toModel(): Product {
        return Product(this.description!!, this.price!!)
    }
}
