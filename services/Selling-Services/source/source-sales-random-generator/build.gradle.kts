plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "com.selling.system.source.random.generator.sales"
version = "1.0.0-SNAPSHOT"



dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("com.fasterxml.jackson.core:jackson-core:${project.findProperty("fasterxmlJacksonVersion")}")
    implementation("com.fasterxml.jackson.core:jackson-databind:${project.findProperty("fasterxmlJacksonVersion")}")
    implementation(project(":shared:shared-module"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}


