when (rootProject.name) {
    "services" -> {
        include(":kafka:orders:kafka-orders-producer")
        include(":kafka:orders:kafka-orders-consumer")
    }
    "kafka" -> {
        include(":orders:kafka-orders-producer")
        include(":orders:kafka-orders-consumer")
    }
    else -> {
        rootProject.name = "orders"
        include(":kafka-orders-producer")
        include(":kafka-orders-consumer")
    }
}