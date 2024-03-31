apply(from = "orders/settings.gradle.kts")
apply(from = "stores/settings.gradle.kts")

when (rootProject.name) {
    "services" -> {
        include(":data:data-manager")
        include(":data:data-stores-manager")
    }
    else -> {
        rootProject.name = "data"
        include(":data-manager")
        include(":data-stores-manager")
    }
}



