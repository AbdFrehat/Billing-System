when (rootProject.name) {
    "services" -> {
        include(":reports:reports-receipt-generator")
        include(":reports:reports-calc-price")
    }
    else -> {
        rootProject.name = "reports"
        include(":reports-receipt-generator")
        include(":reports-calc-price")
    }
}
