when (rootProject.name) {
    "services" -> {
        include(":modify:modify-sales-manager")
        include(":modify:modify-save-sales")
        include(":modify:modify-delete-sales")
        include(":modify:modify-update-sales")
        include(":modify:modify-shared-sales")
    }
    else -> {
        rootProject.name = "modify"
        include(":modify-sales-manager")
        include(":modify-save-sales")
        include(":modify-delete-sales")
        include(":modify-update-sales")
        include(":modify-shared-sales")
    }
}
