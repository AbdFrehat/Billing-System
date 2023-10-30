plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "com.sale.persistence"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(project(":models"))
    implementation("org.projectlombok:lombok:${project.findProperty("lombokVersion")}")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.kafka:spring-kafka")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    annotationProcessor("org.projectlombok:lombok:${project.findProperty("lombokVersion")}")
}