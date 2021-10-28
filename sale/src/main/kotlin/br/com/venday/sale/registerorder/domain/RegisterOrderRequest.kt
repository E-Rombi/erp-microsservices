package br.com.venday.sale.registerorder.domain

import br.com.venday.sale.registerorder.domain.OrderItemRequest
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import kotlin.math.min

data class RegisterOrderRequest(
    @field:NotNull
    @field:Size(min = 1)
    val items: List<OrderItemRequest>
) {
    fun toModel(products: List<SearchProductResponse>): Order {
        val newOrder = Order()

        this.items.forEach { i ->
            val searched = products.find { p -> p.id == i.productId }!!

            newOrder.addItem(
                OrderItem(
                    Product(searched.id, searched.description, searched.price),
                    i.amount,
                    i.discount,
                    i.price,
                    i.price.multiply(i.amount.toBigDecimal()).minus(i.discount)
                )
            )
        }
        newOrder.calcTotals()

        return newOrder
    }
}
