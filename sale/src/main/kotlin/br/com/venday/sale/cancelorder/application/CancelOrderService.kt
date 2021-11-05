package br.com.venday.sale.cancelorder.application

import br.com.venday.sale.registerorder.infra.OrderRepository
import br.com.venday.sale.shared.domain.TopicName
import org.springframework.http.HttpStatus
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CancelOrderService(
    val repository: OrderRepository,
    val kafkaTemplate: KafkaTemplate<String, String>
) {

    fun cancelOrder(id: String) {
        if (!repository.existsById(id)) throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val order = repository.findById(id).get()

        order.toCancelled()
        repository.save(order)

        kafkaTemplate.send(TopicName.CANCEL_ORDER.name(), order.id!!, order.id)
    }
}
