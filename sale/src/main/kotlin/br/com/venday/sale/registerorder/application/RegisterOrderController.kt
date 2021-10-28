package br.com.venday.sale.registerorder.application

import br.com.venday.sale.registerorder.domain.RegisterOrderRequest
import br.com.venday.sale.registerorder.infra.RegisterOrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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

}