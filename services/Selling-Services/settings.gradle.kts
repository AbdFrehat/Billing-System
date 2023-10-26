rootProject.name = "Selling-Services"
include("fetch:fetch-items-ms")
findProject(":fetch:fetch-items-ms")?.name = "fetch-items-ms"
include("models")

pluginManagement {
    plugins {
        val springFrameworkBootVersion: String by settings
        val springDependencyManagementVersion: String by settings
        id("org.springframework.boot").version(springFrameworkBootVersion)
        id("io.spring.dependency-management").version(springDependencyManagementVersion)
    }
}

