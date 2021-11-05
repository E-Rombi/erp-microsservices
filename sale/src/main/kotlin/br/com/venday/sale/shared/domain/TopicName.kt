package br.com.venday.sale.shared.domain

enum class TopicName {

    NEW_ORDER {
        override fun name(): String {
            return "sale-new-order-topic"
        }
    }, CANCEL_ORDER {
        override fun name(): String {
            return "sale-cancel-order-topic"
        }
    };

    internal abstract fun name(): String
}