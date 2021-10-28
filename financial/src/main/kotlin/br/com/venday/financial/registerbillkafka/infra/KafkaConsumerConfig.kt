package br.com.venday.financial.registerbillkafka.infra

import br.com.venday.financial.registerbillkafka.domain.GenerateBillRequest
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
class KafkaConsumerConfig(
    @Value("\${kafka.bootstrapAddress}")
    val bootstrpServer: String,

    @Value("\${kafka.group-id}")
    val groupId: String
) {
    @Bean
    fun consumerFactory(): MutableMap<String, Any> {
        val jsonDeserializer = JsonDeserializer(GenerateBillRequest::class.java)

        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrpServer
        props[ConsumerConfig.GROUP_ID_CONFIG] = groupId
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = jsonDeserializer
        return props
    }

    @Bean
    fun transactionConsumerFactory(): ConsumerFactory<String, GenerateBillRequest>? {
        val stringDeserializer = StringDeserializer()
        val jsonDeserializer: JsonDeserializer<GenerateBillRequest> = JsonDeserializer(
            GenerateBillRequest::class.java, false
        )
        return DefaultKafkaConsumerFactory(consumerFactory(), stringDeserializer, jsonDeserializer)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, GenerateBillRequest> {
        val factory: ConcurrentKafkaListenerContainerFactory<String, GenerateBillRequest> =
            ConcurrentKafkaListenerContainerFactory<String, GenerateBillRequest>()
        factory.consumerFactory = transactionConsumerFactory()!!

        return factory
    }
}