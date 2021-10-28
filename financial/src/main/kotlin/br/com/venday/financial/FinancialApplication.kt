package br.com.venday.financial

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@EnableKafka
@SpringBootApplication
class FinancialApplication

fun main(args: Array<String>) {
	runApplication<FinancialApplication>(*args)
}
