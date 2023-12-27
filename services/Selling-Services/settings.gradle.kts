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

//Data Category
include("data:data-shared-module")
include("data:data-manager")
findProject(":data:data-shared-module")?.name = "data-shared-module"
findProject(":data:data-manager")?.name = "data-manager"

//Get Sub-Category
include("data:get:data-get-manager")
include("data:get:data-sales-get")
include("data:get:data-sales-get-free")
include("data:get:data-sales-get-opt")
findProject(":data:get:data-get-manager")?.name = "data-get-manager"
findProject(":data:get:data-sales-get")?.name = "data-sales-get"
findProject(":data:get:data-sales-get-free")?.name = "data-sales-get-free"
findProject(":data:get:data-sales-get-opt")?.name = "data-sales-get-opt"

//Save Sub-Category
include("data:save:data-save-manager")
include("data:save:data-sale-save")
include("data:save:data-sales-save")
findProject(":data:save:data-save-manager")?.name = "data-save-manager"
findProject(":data:save:data-sale-save")?.name = "data-sale-save"
findProject(":data:save:data-sales-save")?.name = "data-sales-save"

//Delete Sub-Category
include("data:delete:data-delete-manager")
include("data:delete:data-sale-delete")
include("data:delete:data-sales-delete")
include("data:delete:data-sale-delete-query")
findProject(":data:delete:data-delete-manager")?.name = "data-delete-manager"
findProject(":data:delete:data-sale-delete")?.name = "data-sale-delete"
findProject(":data:delete:data-sales-delete")?.name = "data-sales-delete"
findProject(":data:delete:data-sale-delete-query")?.name = "data-sale-delete-query"

//Update Sub-Category
include("data:update:data-update-manager")
include("data:update:data-sale-update")
include("data:update:data-sales-update")
findProject(":data:update:data-update-manager")?.name = "data-update-manager"
findProject(":data:update:data-sale-update")?.name = "data-sale-update"
findProject(":data:update:data-sales-update")?.name = "data-sales-update"

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
include(":export:export-data-manager")
findProject(":export:export-data-manager")?.name = "export-data-manager"
include(":export:export-data-json")
findProject(":export:export-data-json")?.name = "export-data-json"
include(":export:export-data-xml")
findProject(":export:export-data-xml")?.name = "export-data-xml"
include(":export:export-data-csv")
findProject(":export:export-data-csv")?.name = "export-data-csv"
include(":export:export-data-pdf")
findProject(":export:export-data-pdf")?.name = "export-data-pdf"
include(":export:export-data-xlsx")
findProject(":export:export-data-xlsx")?.name = "export-data-xlsx"
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

