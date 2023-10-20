rootProject.name = "Selling-Services"
include("fetch:ms-get-items")
include("modules:spring-boot")
include("modules:spring-boot:reactive-web")
findProject(":fetch:ms-get-items")?.name = "ms-get-items"
findProject(":modules:spring-boot:reactive-web")?.name = "reactive-web"
findProject(":modules:spring-boot")?.name = "ms-get-items"
