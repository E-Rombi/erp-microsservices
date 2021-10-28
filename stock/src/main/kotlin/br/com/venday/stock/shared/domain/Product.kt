package br.com.venday.stock.shared.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Document(collection = "product")
class Product(

    @field:NotBlank
    val description: String,

    @field:NotNull
    @field:Positive
    val price: BigDecimal,
) {

    @Id
    var id: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (description != other.description) return false
        if (price != other.price) return false

        return true
    }

    override fun hashCode(): Int {
        var result = description.hashCode()
        result = 31 * result + price.hashCode()
        return result
    }


}