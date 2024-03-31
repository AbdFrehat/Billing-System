when (rootProject.name) {
    "services" -> {
        include(":data:orders:delete:data-delete-orders-manager")
        include(":data:orders:delete:data-delete-order")
        include(":data:orders:delete:data-delete-orders")
        include(":data:orders:delete:data-delete-search-orders")
    }
    "data" -> {
        include(":orders:delete:data-delete-orders-manager")
        include(":orders:delete:data-delete-order")
        include(":orders:delete:data-delete-orders")
        include(":orders:delete:data-delete-search-orders")
    }
    "orders" -> {
        include(":delete:data-delete-orders-manager")
        include(":delete:data-delete-order")
        include(":delete:data-delete-orders")
        include(":delete:data-delete-search-orders")
    }
    else -> {
        rootProject.name = "delete"
        include(":data-delete-orders-manager")
        include(":data-delete-order")
        include(":data-delete-orders")
        include(":data-delete-search-orders")
    }
}
