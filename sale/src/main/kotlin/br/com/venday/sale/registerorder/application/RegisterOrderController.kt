package br.com.venday.sale.registerorder.application

import br.com.venday.sale.registerorder.domain.RegisterOrderRequest
import br.com.venday.sale.registerorder.infra.RegisterOrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/sales/orders")
class RegisterOrderController(
    val service: RegisterOrderService
) {

    @PostMapping
    fun register(@RequestBody @Valid request: RegisterOrderRequest,
                    uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<Any> {
        val idNewOrder = service.registerNewOrder(request)
        val uri = uriComponentsBuilder.path("/sales/orders/{id}").buildAndExpand(idNewOrder).toUri()

        return ResponseEntity.created(uri).build()
    }

    @PostMapping("/{id}/invoice")
    fun invoiceOrder(@PathVariable id: String): ResponseEntity<Any> {
        service.invoiceOrder(id)

        return ResponseEntity.ok().build()
    }
}