when (rootProject.name) {
    "services" -> {
        include(":data:orders:save:data-save-orders-manager")
        include(":data:orders:save:data-save-order")
        include(":data:orders:save:data-save-orders")
    }
    "data" -> {
        include(":orders:save:data-save-orders-manager")
        include(":orders:save:data-save-order")
        include(":orders:save:data-save-orders")
    }
    "orders" -> {
        include(":save:data-save-orders-manager")
        include(":save:data-save-order")
        include(":save:data-save-orders")
    }
    else -> {
        rootProject.name = "save"
        include(":data-save-orders-manager")
        include(":data-save-order")
        include(":data-save-orders")
    }
}
