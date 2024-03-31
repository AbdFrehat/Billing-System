when (rootProject.name) {
    "services" -> {
        include(":data:orders:get:data-get-orders-manager")
        include(":data:orders:get:data-get-search-orders")
        include(":data:orders:get:data-get-free-orders")
        include(":data:orders:get:data-get-operation-orders")
    }
    "data" -> {
        include(":orders:get:data-get-orders-manager")
        include(":orders:get:data-get-search-orders")
        include(":orders:get:data-get-free-orders")
        include(":orders:get:data-get-operation-orders")
    }
    "orders" -> {
        include(":get:data-get-orders-manager")
        include(":get:data-get-search-orders")
        include(":get:data-get-free-orders")
        include(":get:data-get-operation-orders")
    }
    else -> {
        rootProject.name = "get"
        include(":data-get-orders-manager")
        include(":data-get-search-orders")
        include(":data-get-free-orders")
        include(":data-get-operation-orders")
    }
}
