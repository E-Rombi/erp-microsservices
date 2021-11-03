package br.com.venday.sale.shared.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal
import java.time.Clock
import java.time.Instant

@Document(collection = "order")
class Order {

    @Id
    var id: String? = null
    var createdAt: Instant = Instant.now(Clock.systemUTC())
    var total: BigDecimal = BigDecimal.ZERO
    var status: OrderStatus = OrderStatus.OPENED
    var items: MutableList<OrderItem> = mutableListOf()

    fun addItem(item: OrderItem) {
        this.items.add(item)
    }

    fun calcTotals() {
        this.items.forEach { item ->
            this.total = this.total.plus(item.total)
        }
    }

    fun invoiceOrder() {
        if (this.status == OrderStatus.BILLED) throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY)

        this.status = OrderStatus.BILLED
    }

    fun toCancelled() {
        this.status.toCancelled(this)
    }
}