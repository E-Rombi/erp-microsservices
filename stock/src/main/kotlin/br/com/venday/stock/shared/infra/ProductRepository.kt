package br.com.venday.stock.shared.infra

import br.com.venday.stock.shared.domain.Product
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
interface ProductRepository : MongoRepository<Product, String> {

    fun existsByDescriptionAndPrice(description: String, price: BigDecimal): Boolean
}