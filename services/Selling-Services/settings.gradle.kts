rootProject.name = "Selling-Services"

include("fetch:fetch-items-ms")
include("fetch:fetch-stores-ms")
include("fetch:fetch-customer-ms")
include("reports:receipt-generator-ms")
include("reports:calc-price-ms")
include("persistence:kafka-sale-producer-ms")
include("persistence:kafka-sale-consumer-ms")
include("source:sales-source-ms")
include("models")
findProject(":fetch:fetch-items-ms")?.name = "fetch-items-ms"
findProject(":fetch:fetch-stores-ms")?.name = "fetch-stores-ms"
findProject(":fetch:fetch-customer-ms")?.name = "fetch-customer-ms"
findProject(":reports:receipt-generator-ms")?.name = "receipt-generator-ms"
findProject(":reports:calc-price-ms")?.name = "calc-price-ms"
findProject(":persistence:kafka-sale-producer-ms")?.name = "kafka-sale-producer-ms"
findProject(":persistence:kafka-sale-consumer-ms")?.name = "kafka-sale-consumer-ms"
findProject(":source:sales-source-ms")?.name = "sales-source-ms"

pluginManagement {
    plugins {
        val springFrameworkBootVersion: String by settings
        val springDependencyManagementVersion: String by settings
        id("org.springframework.boot").version(springFrameworkBootVersion)
        id("io.spring.dependency-management").version(springDependencyManagementVersion)
    }
}

