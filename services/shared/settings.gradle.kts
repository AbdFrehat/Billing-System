when (rootProject.name) {
    "services" -> {
        include(":shared:orderizer-core")

    }
    else -> {
        rootProject.name = "shared"
        include(":orderizer-core")

    }
}
