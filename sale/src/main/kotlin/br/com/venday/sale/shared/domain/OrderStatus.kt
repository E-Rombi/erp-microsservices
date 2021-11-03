package br.com.venday.sale.shared.domain

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

enum class OrderStatus {

    OPENED {
        override fun toBilled(order: Order) {
            order.status = BILLED
        }

        override fun toCancelled(order: Order) {
            order.status = CANCELLED
        }
    }, BILLED {
        override fun toBilled(order: Order) {
            throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY)
        }

        override fun toCancelled(order: Order) {
            order.status = CANCELLED
        }
    }, CANCELLED {
        override fun toBilled(order: Order) {
            throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY)
        }

        override fun toCancelled(order: Order) {
            throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY)
        }
    };

    abstract fun toBilled(order: Order)
    abstract fun toCancelled(order: Order)

}
