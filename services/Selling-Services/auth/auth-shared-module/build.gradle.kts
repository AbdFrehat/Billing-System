plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "com.selling.system.auth.shared.module"
version = "1.0.0-SNAPSHOT"


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.postgresql:r2dbc-postgresql:1.0.4.RELEASE")
    runtimeOnly("org.postgresql:postgresql")
    implementation("jakarta.validation:jakarta.validation-api:${project.findProperty("jakartaValidationApiVersion")}")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:${project.findProperty("springDocOpenAPIVersion")}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation(project(":shared:shared-module"))
    testImplementation("io.projectreactor:reactor-test")
}


