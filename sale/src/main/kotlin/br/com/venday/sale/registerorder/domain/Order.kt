package br.com.venday.sale.registerorder.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.Clock
import java.time.Instant

@Document(collection = "order")
class Order {

    @Id
    var id: String? = null
    val createdAt: Instant = Instant.now(Clock.systemUTC())
    val items: MutableList<OrderItem> = mutableListOf()
    var total: BigDecimal = BigDecimal.ZERO

    fun addItem(item: OrderItem) {
        this.items.add(item)
    }

    fun calcTotals() {
        this.items.forEach { item ->
            this.total = this.total.plus(item.total)
        }
    }
}