plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "com.sales.fetch"
version = "1.0-SNAPSHOT"


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("jakarta.validation:jakarta.validation-api:${project.findProperty("jakartaValidationApiVersion")}")
    implementation("org.projectlombok:lombok:${project.findProperty("lombokVersion")}")
    implementation(project(":models"))
    annotationProcessor("org.projectlombok:lombok:${project.findProperty("lombokVersion")}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
}
