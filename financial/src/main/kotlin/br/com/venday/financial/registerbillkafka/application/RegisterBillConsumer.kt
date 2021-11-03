package br.com.venday.financial.registerbillkafka.application

import br.com.venday.financial.registerbillkafka.domain.GenerateBillRequest
import br.com.venday.financial.shared.infra.BillRepository
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@Service
class RegisterBillConsumer(
    val repository: BillRepository
) {

    @KafkaListener(topics = ["sale-new-order-topic"], containerFactory = "kafkaListenerContainerFactory")
    fun createBill(@Payload message: GenerateBillRequest) {
        val bill = message.toBill()

        repository.save(bill)
    }
}