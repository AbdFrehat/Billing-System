rootProject.name = "services"

//Reports Category
include("reports:reports-receipt-generator")
include("reports:reports-calc-price")
findProject(":reports:reports-receipt-generator")?.name = "reports-receipt-generator"
findProject(":reports:reports-calc-price")?.name = "reports-calc-price"

//Kafka Persistence Category
include("kafka:sales:kafka-sale-producer")
include("kafka:sales:kafka-sale-consumer")
findProject(":kafka:sales:kafka-sale-producer")?.name = "kafka-sale-producer"
findProject(":kafka:sales:kafka-sale-consumer")?.name = "kafka-sale-consumer"


//Source Category
include("source:source-sales-pull")
include("source:source-sales-push")
include("source:source-sales-random-generator")
findProject(":source:source-sales-pull")?.name = "source-sales-pull"
findProject(":source:source-sales-push")?.name = "source-sales-push"
findProject(":source:source-sales-random-generator")?.name = "source-sales-random-generator"

//Data Category
include("data:data-shared-module")
include("data:data-manager")
findProject(":data:data-shared-module")?.name = "data-shared-module"
findProject(":data:data-manager")?.name = "data-manager"

//Get Sub-Category
include("data:get:data-get-manager")
include("data:get:data-get-search-orders")
include("data:get:data-sales-get-free")
include("data:get:data-sales-get-opt")
findProject(":data:get:data-get-manager")?.name = "data-get-manager"
findProject(":data:get:data-sales-get")?.name = "data-sales-get"
findProject(":data:get:data-sales-get-free")?.name = "data-sales-get-free"
findProject(":data:get:data-sales-get-opt")?.name = "data-sales-get-opt"

//Save Sub-Category
include("data:save:data-save-orders-manager")
include("data:save:data-save-order")
include("data:save:data-save-orders")
findProject(":data:save:data-save-orders-manager")?.name = "data-save-orders-manager"
findProject(":data:save:data-save-order")?.name = "data-save-order"
findProject(":data:save:data-save-orders")?.name = "data-save-orders"

//Delete Sub-Category
include("data:delete:data-delete-orders-manager")
include("data:delete:data-delete-order")
include("data:delete:data-delete-orders")
include("data:delete:data-delete-search-orders")
findProject(":data:delete:data-delete-orders-manager")?.name = "data-delete-orders-manager"
findProject(":data:delete:data-delete-order")?.name = "data-delete-order"
findProject(":data:delete:data-delete-orders")?.name = "data-delete-orders"
findProject(":data:delete:data-delete-search-orders")?.name = "data-delete-search-orders"

//Update Sub-Category
include("data:update:data-update-orders-manager")
include("data:update:data-update-order")
include("data:update:data-update-orders")
findProject(":data:update:data-update-orders-manager")?.name = "data-update-orders-manager"
findProject(":data:update:data-update-order")?.name = "data-update-order"
findProject(":data:update:data-update-orders")?.name = "data-update-orders"

//Shared
include(":shared:shared-module")
findProject(":shared:shared-module")?.name = "shared-module"

//Modify
include(":modify:modify-sales-manager")
findProject(":modify:modify-sales-manager")?.name = "modify-sales-manager"
include(":modify:modify-save-sales")
findProject(":modify:modify-save-sales")?.name = "modify-save-sales"
include(":modify:modify-delete-sales")
findProject(":modify:modify-delete-sales")?.name = "modify-delete-sales"
include(":modify:modify-update-sales")
findProject(":modify:modify-update-sales")?.name = "modify-update-sales"
include(":modify:modify-shared-sales")
findProject(":modify:modify-shared-sales")?.name = "modify-shared-sales"


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


//Auth
include(":auth:auth-manager")
findProject(":auth:auth-manager")?.name = "auth-manager"
include(":auth:auth-users-manager")
findProject(":auth:auth-users-manager")?.name = "auth-users-manager"
include(":auth:auth-profiles-manager")
findProject(":auth:auth-profiles-manager")?.name = "auth-profiles-manager"
include(":auth:auth-clients-manager")
findProject(":auth:auth-clients-manager")?.name = "auth-clients-manager"
include(":auth:auth-shared-module")
findProject(":auth:auth-shared-module")?.name = "auth-shared-module"