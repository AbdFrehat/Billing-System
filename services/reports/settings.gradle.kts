when (rootProject.name) {
    "services" -> {
        include(":reports:reports-receipt-generator")
    }
    else -> {
        rootProject.name = "reports"
        include(":reports-receipt-generator")
    }
}
