when (rootProject.name) {
    "services" -> {
        include(":data:orders:update:data-update-orders-manager")
        include(":data:orders:update:data-update-order")
        include(":data:orders:update:data-update-orders")
    }
    "data" -> {
        include(":orders:update:data-update-orders-manager")
        include(":orders:update:data-update-order")
        include(":orders:update:data-update-orders")
    }
    "orders" -> {
        include(":update:data-update-orders-manager")
        include(":update:data-update-order")
        include(":update:data-update-orders")
    }
    else -> {
        rootProject.name = "update"
        include(":data-update-orders-manager")
        include(":data-update-order")
        include(":data-update-orders")
    }
}
