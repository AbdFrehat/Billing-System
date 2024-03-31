when (rootProject.name) {
    "services" -> {
        include(":kafka:orders:kafka-sale-producer")
        include(":kafka:orders:kafka-sale-consumer")
    }
    "kafka" -> {
        include(":orders:kafka-sale-producer")
        include(":orders:kafka-sale-consumer")
    }
    else -> {
        rootProject.name = "orders"
        include(":kafka-sale-producer")
        include(":kafka-sale-consumer")
    }
}