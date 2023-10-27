rootProject.name = "Selling-Services"

include("fetch:fetch-items-ms")
include("fetch:fetch-sales-ms")
include("models")
findProject(":fetch:fetch-items-ms")?.name = "fetch-items-ms"
findProject(":fetch:fetch-sales-ms")?.name = "fetch-sales-ms"

pluginManagement {
    plugins {
        val springFrameworkBootVersion: String by settings
        val springDependencyManagementVersion: String by settings
        id("org.springframework.boot").version(springFrameworkBootVersion)
        id("io.spring.dependency-management").version(springDependencyManagementVersion)
    }
}

