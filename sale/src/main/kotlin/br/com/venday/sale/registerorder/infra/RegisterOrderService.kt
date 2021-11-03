package br.com.venday.sale.registerorder.infra

import br.com.venday.sale.registerorder.domain.GenerateBillRequest
import br.com.venday.sale.registerorder.domain.RegisterOrderRequest
import br.com.venday.sale.shared.domain.TopicName
import feign.FeignException
import org.springframework.http.HttpStatus
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
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

            return order.id!!
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }
    }

    fun invoiceOrder(id: String) {
        val possibleOrder = repository.findById(id)
        if (possibleOrder.isEmpty) throw ResponseStatusException(HttpStatus.NOT_FOUND)

        val order = possibleOrder.get()

        order.invoiceOrder()
        repository.save(order)

        kafkaTemplate.send(TopicName.NEW_ORDER.name(), order.id!!, GenerateBillRequest(order.id!!, order.createdAt, order.total))
    }
}
