rootProject.name = "Selling-Services"
include("fetch:ms-get-items")
findProject(":fetch:ms-get-items")?.name = "ms-get-items"


pluginManagement {
    plugins {
        val springFrameworkBootVersion: String by settings
        val springDependencyManagement: String by settings
        id("org.springframework.boot").version(springFrameworkBootVersion)
        id("io.spring.dependency-management").version(springDependencyManagement)
    }
}
