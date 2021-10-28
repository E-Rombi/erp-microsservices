package br.com.venday.financial.registerbillkafka.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document(collection = "bill")
class Bill(
    val order: Order,
    val total: BigDecimal
) {

    @Id
    var id: String? = null
    val status: StatusBill = StatusBill.OPENED
}