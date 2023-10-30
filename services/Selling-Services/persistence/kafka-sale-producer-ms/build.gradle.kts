plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "com.sale.persistence"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation("org.projectlombok:lombok:${project.findProperty("lombokVersion")}")
    implementation(project(":models"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.kafka:spring-kafka")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    annotationProcessor("org.projectlombok:lombok:${project.findProperty("lombokVersion")}")
}