package br.com.venday.sale.registerorder.infra

import br.com.venday.sale.registerorder.domain.GenerateBillRequest
import br.com.venday.sale.shared.domain.TopicName
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaTopicConfig(
    @Value(value = "\${kafka.bootstrapAddress}")
    val bootstrapAddress: String
) {

    @Bean
    fun producerFactory(): ProducerFactory<String?, GenerateBillRequest?> {
        val configProps: MutableMap<String, Any> = HashMap()
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java

        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun producerCancelOrderFactory(): ProducerFactory<String?, String?> {
        val configProps: MutableMap<String, Any> = HashMap()
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java

        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String?, GenerateBillRequest?>? {
        return KafkaTemplate(producerFactory())
    }

    @Bean
    fun kafkaCancelOrderTemplate(): KafkaTemplate<String?, String?>? {
        return KafkaTemplate(producerCancelOrderFactory())
    }

    @Bean
    fun newOrderTopic(): List<NewTopic> {
        return listOf(NewTopic(TopicName.NEW_ORDER.name(), 1, 1.toShort()),
                        NewTopic(TopicName.CANCEL_ORDER.name(), 1, 1.toShort()))
    }
}

