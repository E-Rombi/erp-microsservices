package br.com.venday.sale.registerorder.infra

import br.com.venday.sale.registerorder.domain.SearchProductResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "ms-stock")
interface StockClient {

    @GetMapping("/products/search")
    fun searchIds(@RequestParam productIds: List<String>): List<SearchProductResponse>
}