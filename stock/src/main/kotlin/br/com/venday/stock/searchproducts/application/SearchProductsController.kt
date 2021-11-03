package br.com.venday.stock.searchproducts.application

import br.com.venday.stock.searchproducts.domain.SearchProductResponse
import br.com.venday.stock.shared.infra.ProductRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products/search")
class SearchProductsController(
    val repository: ProductRepository
) {

    @GetMapping
    fun search(@RequestParam productIds: List<String>): ResponseEntity<List<SearchProductResponse>> {
        val response = repository.findAllById(productIds).map { p -> SearchProductResponse(p.id!!, p.description, p.price) }

        println("TA RODANDO AQUI LUCCAO")
        return ResponseEntity.ok(response)
    }

}