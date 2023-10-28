rootProject.name = "Selling-Services"

include("fetch:fetch-items-ms")
include("fetch:fetch-stores-ms")
include("fetch:fetch-customer-ms")
include("reports:receipt-generator-ms")
include("models")
findProject(":fetch:fetch-items-ms")?.name = "fetch-items-ms"
findProject(":fetch:fetch-stores-ms")?.name = "fetch-stores-ms"
findProject(":fetch:fetch-customer-ms")?.name = "fetch-customer-ms"
findProject(":reports:receipt-generator-ms")?.name = "receipt-generator-ms"

pluginManagement {
    plugins {
        val springFrameworkBootVersion: String by settings
        val springDependencyManagementVersion: String by settings
        id("org.springframework.boot").version(springFrameworkBootVersion)
        id("io.spring.dependency-management").version(springDependencyManagementVersion)
    }
}

