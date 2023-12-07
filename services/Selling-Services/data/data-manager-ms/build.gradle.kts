plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "com.selling.system.data.manager.sales"
version = "1.0.0-SNAPSHOT"


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("jakarta.validation:jakarta.validation-api:${project.findProperty("jakartaValidationApiVersion")}")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:${project.findProperty("springDocOpenAPIVersion")}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation(project(":shared:shared-module"))
    testImplementation("io.projectreactor:reactor-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${project.findProperty("springCloudVersion")}")
    }
}

