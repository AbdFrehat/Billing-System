when (rootProject.name) {
    "services" -> {
        include(":source:source-pull-orders")
        include(":source:source-push-orders")
        include(":source:source-orders-random-generator")
    }
    else -> {
        rootProject.name = "source"
        include(":source-pull-orders")
        include(":source-push-orders")
        include(":source-orders-random-generator")
    }
}
