package br.com.venday.stock.registerproduct.application

import br.com.venday.stock.registerproduct.domain.RegisterProductRequest
import br.com.venday.stock.shared.infra.ProductRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/products")
class RegisterProductController(
    val repository: ProductRepository
) {

    @PostMapping
    fun register(@RequestBody @Valid request: RegisterProductRequest,
                 uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<Any> {
        if (repository.existsByDescriptionAndPrice(request.description!!, request.price!!))
            return ResponseEntity.unprocessableEntity().build()

        val product = request.toModel()
        repository.save(product)

        val uri = uriComponentsBuilder.path("/products/{id}").buildAndExpand(product.id).toUri()
        return ResponseEntity.created(uri).build()
    }

}