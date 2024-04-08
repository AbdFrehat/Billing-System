when (rootProject.name) {
    "services" -> {
        include(":modify:modify-manager")
    }
    else -> {
        rootProject.name = "modify"
        include(":modify-manager")
    }
}
