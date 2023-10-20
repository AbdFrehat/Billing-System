plugins {
    java
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "com.modules.spring"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

repositories {
    mavenCentral()
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
}

tasks.test {
    useJUnitPlatform()
}

