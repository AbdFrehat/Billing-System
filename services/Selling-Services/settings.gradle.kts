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
include("data:data-shared-module")
include("data:data-manager-ms")
findProject(":data:data-shared-module")?.name = "data-shared-module"
findProject(":data:data-manager-ms")?.name = "data-manager-ms"

//get Sub-Category
include("data:get:data-get-manager-ms")
include("data:get:data-get-ms")
include("data:get:data-get-free-ms")
include("data:get:data-get-opt-ms")
findProject(":data:get:data-get-manager-ms")?.name = "data-get-manager-ms"
findProject(":data:get:data-get-ms")?.name = "data-get-ms"
findProject(":data:get:data-get-free-ms")?.name = "data-get-free-ms"
findProject(":data:get:data-get-opt-ms")?.name = "data-get-opt-ms"

//save Sub-Category
include("data:save:data-save-manager-ms")
include("data:save:data-save-ms")
include("data:save:data-save-multi-ms")
findProject(":data:save:data-save-manager-ms")?.name = "data-save-manager-ms"
findProject(":data:save:data-save-ms")?.name = "data-save-ms"
findProject(":data:save:data-save-multi-ms")?.name = "data-save-multi-ms"

//delete Sub-Category
include("data:delete:data-delete-ms")
findProject(":data:delete:data-delete-ms")?.name = "data-delete-ms"

//update Sub-Category
include("data:update:data-update-manager-ms")
include("data:update:data-update-ms")
include("data:update:data-update-multi-ms")
findProject(":data:update:data-update-manager-ms")?.name = "data-update-manager-ms"
findProject(":data:update:data-update-ms")?.name = "data-update-ms"
findProject(":data:update:data-update-multi-ms")?.name = "data-update-multi-ms"

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

