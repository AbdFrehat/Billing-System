when (rootProject.name) {
    "services" -> {
        include(":kafka:orders:kafka-sale-producer")
        include(":kafka:orders:kafka-orders-consumer")
    }
    "kafka" -> {
        include(":orders:kafka-sale-producer")
        include(":orders:kafka-orders-consumer")
    }
    else -> {
        rootProject.name = "orders"
        include(":kafka-sale-producer")
        include(":kafka-orders-consumer")
    }
}