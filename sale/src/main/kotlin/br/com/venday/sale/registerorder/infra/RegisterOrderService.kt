package br.com.venday.sale.registerorder.infra

import br.com.venday.sale.registerorder.domain.GenerateBillRequest
import br.com.venday.sale.registerorder.domain.RegisterOrderRequest
import feign.FeignException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.util.concurrent.ListenableFutureCallback
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@Service
class RegisterOrderService(
    val client: StockClient,
    val repository: OrderRepository,
    val kafkaTemplate: KafkaTemplate<String, GenerateBillRequest>
) {

    fun registerNewOrder(@Valid request: RegisterOrderRequest): String {
        try {
            val ids = request.items.map { it.productId }
            val products = client.searchIds(ids)
            if (products.size != request.items.size) throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY)

            val order = request.toModel(products)
            repository.save(order)

            kafkaTemplate.send(TopicName.NEW_ORDER.name(), order.id!!, GenerateBillRequest(order.id!!, order.createdAt, order.total))

            return order.id!!
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }
    }
}
