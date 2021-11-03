package br.com.venday.sale.cancelorder.application

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sales/orders")
class CancelOrderController(
    val service: CancelOrderService
) {

    @PostMapping("/{id}/cancel")
    fun cancelOrder(@PathVariable id: String): ResponseEntity<Any> {
        service.cancelOrder(id)

        return ResponseEntity.ok().build()
    }
}