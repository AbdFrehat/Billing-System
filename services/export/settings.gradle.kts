when (rootProject.name) {
    "services" -> {
        include(":export:export-data-manager")
        include(":export:export-data-json")
        include(":export:export-data-xml")
        include(":export:export-data-csv")
        include(":export:export-data-pdf")
        include(":export:export-data-xlsx")
        include(":export:export-data-shared-module")
    }
    else -> {
        rootProject.name = "export"
        include(":export-data-manager")
        include(":export-data-json")
        include(":export-data-xml")
        include(":export-data-csv")
        include(":export-data-pdf")
        include(":export-data-xlsx")
        include(":export-data-shared-module")
    }
}
