rootProject.name = "Selling-Services"

//Reports Category
include("reports:receipt-generator-ms")
include("reports:calc-price-ms")
findProject(":reports:receipt-generator-ms")?.name = "receipt-generator-ms"
findProject(":reports:calc-price-ms")?.name = "calc-price-ms"

//Kafka Category
include("kafka:sales:kafka-sale-producer-ms")
include("kafka:sales:kafka-sale-consumer-ms")
findProject(":kafka:sales:kafka-sale-producer-ms")?.name = "kafka-sale-producer-ms"
findProject(":kafka:sales:kafka-sale-consumer-ms")?.name = "kafka-sale-consumer-ms"


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
include("data:get:sales-get-ms")
include("data:get:sales-get-free-ms")
include("data:get:sales-get-opt-ms")
findProject(":data:get:data-get-manager-ms")?.name = "data-get-manager-ms"
findProject(":data:get:sales-get-ms")?.name = "sales-get-ms"
findProject(":data:get:sales-get-free-ms")?.name = "sales-get-free-ms"
findProject(":data:get:sales-get-opt-ms")?.name = "sales-get-opt-ms"

//save Sub-Category
include("data:save:data-save-manager-ms")
include("data:save:sale-save-ms")
include("data:save:sales-save-ms")
findProject(":data:save:data-save-manager-ms")?.name = "data-save-manager-ms"
findProject(":data:save:sale-save-ms")?.name = "sale-save-ms"
findProject(":data:save:sales-save-ms")?.name = "sales-save-ms"

//delete Sub-Category
include("data:delete:data-delete-manager-ms")
include("data:delete:sale-delete-ms")
include("data:delete:sales-delete-ms")
include("data:delete:sale-delete-query-ms")
findProject(":data:delete:data-delete-manager-ms")?.name = "data-delete-manager-ms"
findProject(":data:delete:sale-delete-ms")?.name = "sale-delete-ms"
findProject(":data:delete:sales-delete-ms")?.name = "sales-delete-ms"
findProject(":data:delete:sale-delete-query-ms")?.name = "sale-delete-query-ms"

//update Sub-Category
include("data:update:data-update-manager-ms")
include("data:update:sale-update-ms")
include("data:update:sales-update-ms")
findProject(":data:update:data-update-manager-ms")?.name = "data-update-manager-ms"
findProject(":data:update:sale-update-ms")?.name = "sale-update-ms"
findProject(":data:update:sales-update-ms")?.name = "sales-update-ms"

//Shared
include(":shared:shared-module")
include("shared:eureka-server")
findProject(":shared:shared-module")?.name = "shared-module"
findProject(":eureka-server")?.name = "eureka-server"


//Modify
include(":modify:modify-router-sales-manager")
findProject(":modify:modify-router-sales-manager")?.name = "modify-router-sales-manager"
include(":modify:modify-save-sales-ms")
findProject(":modify:modify-save-sales-ms")?.name = "modify-save-sales-ms"
include(":modify:modify-delete-sales-ms")
findProject(":modify:modify-delete-sales-ms")?.name = "modify-delete-sales-ms"
include(":modify:modify-update-sales-ms")
findProject(":modify:modify-update-sales-ms")?.name = "modify-update-sales-ms"
include(":modify:modify-shared-sales-ms")
findProject(":modify:modify-shared-sales-ms")?.name = "modify-shared-sales-ms"


//Export
include(":export:export-data-json")
findProject(":export:export-data-json")?.name = "export-data-json"
include(":export:export-data-xml")
findProject(":export:export-data-xml")?.name = "export-data-xml"
include(":export:export-data-csv")
findProject(":export:export-data-csv")?.name = "export-data-csv"
include(":export:export-data-shared-module")
findProject(":export:export-data-shared-module")?.name = "export-data-shared-module"


pluginManagement {
    plugins {
        val springFrameworkBootVersion: String by settings
        val springDependencyManagementVersion: String by settings
        id("org.springframework.boot").version(springFrameworkBootVersion)
        id("io.spring.dependency-management").version(springDependencyManagementVersion)
    }
}

