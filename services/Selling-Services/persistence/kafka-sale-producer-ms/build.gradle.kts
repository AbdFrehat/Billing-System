plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "com.sale.persistence"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation("org.projectlombok:lombok:${project.findProperty("lombokVersion")}")
    implementation(project(":models"))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.kafka:spring-kafka")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    annotationProcessor("org.projectlombok:lombok:${project.findProperty("lombokVersion")}")
}