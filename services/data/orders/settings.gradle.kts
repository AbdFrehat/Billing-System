apply(from = "save/settings.gradle.kts")
apply(from = "delete/settings.gradle.kts")
apply(from = "update/settings.gradle.kts")
apply(from = "get/settings.gradle.kts")

when (rootProject.name) {
    "services" -> include(":data:orders:data-sync-orders")
    "data" -> include(":orders:data-sync-orders")
    else -> {
        rootProject.name = "orders"
        include(":data-sync-orders")
    }
}
