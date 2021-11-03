package br.com.venday.sale.registerorder.infra

import br.com.venday.sale.shared.domain.Order
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : MongoRepository<Order, String> {

}