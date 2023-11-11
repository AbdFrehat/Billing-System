plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "com.selling.system.shared.module"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation(project(":models"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

