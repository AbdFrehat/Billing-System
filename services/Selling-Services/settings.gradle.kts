rootProject.name = "Selling-Services"

//Fetch Category
include("fetch:fetch-items-ms")
include("fetch:fetch-stores-ms")
include("fetch:fetch-customer-ms")
include("fetch:fetch-eureka-server")
findProject(":fetch:fetch-items-ms")?.name = "fetch-items-ms"
findProject(":fetch:fetch-stores-ms")?.name = "fetch-stores-ms"
findProject(":fetch:fetch-customer-ms")?.name = "fetch-customer-ms"
findProject(":fetch:fetch-eureka-server")?.name = "fetch-eureka-server"

//Reports Category
include("reports:receipt-generator-ms")
include("reports:calc-price-ms")
include("reports:report-eureka-server")
findProject(":reports:receipt-generator-ms")?.name = "receipt-generator-ms"
findProject(":reports:calc-price-ms")?.name = "calc-price-ms"
findProject(":reports:report-eureka-server")?.name = "report-eureka-server"

//Kafka Category
include("kafka:sales:kafka-sale-producer-ms")
include("kafka:sales:kafka-sale-consumer-ms")
include("kafka:kafka-eureka-server")
findProject(":kafka:sales:kafka-sale-producer-ms")?.name = "kafka-sale-producer-ms"
findProject(":kafka:sales:kafka-sale-consumer-ms")?.name = "kafka-sale-consumer-ms"
findProject(":kafka:persistence-eureka-server")?.name = "kafka-eureka-server"


//Source Category
include("source:sales-pull-ms")
include("source:sales-push-ms")
include("source:sales-random-generator")
findProject(":source:sales-pull-ms")?.name = "sales-pull-ms"
findProject(":source:sales-push-ms")?.name = "sales-push-ms"
findProject(":source:sales-random-generator")?.name = "sales-random-generator"

//Query Category

include("data:delete:sales-delete-ms")
include("data:save:sales-save-ms")
include("data:get:get-eureka-server")
include("data:get:data-get-manager-ms")
include("data:get:sales-get-ms")
include("data:get:sales-free-get-ms")
include("data:get:sales-opt-get-ms")
include("data:update:sales-update-ms")
include("data:data-shared-module")
include("data:data-manager-ms")


findProject(":data:delete:sales-delete-ms")?.name = "sales-delete-ms"
findProject(":data:save:sales-save-ms")?.name = "sales-save-ms"
findProject(":data:get:data-get-manager-ms")?.name = "data-get-manager-ms"
findProject(":data:get:sales-get-ms")?.name = "sales-get-ms"
findProject(":data:get:sales-free-get-ms")?.name = "sales-free-get-ms"
findProject(":data:get:sales-opt-get-ms")?.name = "sales-opt-get-ms"
findProject(":data:update:sales-update-ms")?.name = "sales-update-ms"
findProject(":data:data-shared-module")?.name = "data-shared-module"
findProject(":data:data-manager-ms")?.name = "data-manager-ms"



//Commons
include("shared-module")
include("skeleton")


pluginManagement {
    plugins {
        val springFrameworkBootVersion: String by settings
        val springDependencyManagementVersion: String by settings
        id("org.springframework.boot").version(springFrameworkBootVersion)
        id("io.spring.dependency-management").version(springDependencyManagementVersion)
    }
}

