plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "com.selling.system.data.get.manager.sales"
version = "1.0.0-SNAPSHOT"


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("jakarta.validation:jakarta.validation-api:${project.findProperty("jakartaValidationApiVersion")}")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:${project.findProperty("springDocOpenAPIVersion")}")
    implementation(project(":shared-module"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner:${project.findProperty("springCloudContractStubRunnerVersion")}")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${project.findProperty("springCloudVersion")}")
    }
}