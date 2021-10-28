package br.com.venday.financial.registerbillkafka.infra

import br.com.venday.financial.registerbillkafka.domain.Bill
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BillRepository : MongoRepository<Bill, String> {
}