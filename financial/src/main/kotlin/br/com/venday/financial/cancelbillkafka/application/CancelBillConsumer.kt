package br.com.venday.financial.cancelbillkafka.application

import br.com.venday.financial.shared.infra.BillRepository
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@Service
class CancelBillConsumer(
    val repository: BillRepository
) {

    @KafkaListener(topics = ["sale-cancel-order-topic"], containerFactory = "kafkaListenerContainerCancelOrderFactory")
    fun deleteBill(@Payload idOrder: String) {

        repository.deleteByOrder_Id(idOrder)
    }
}