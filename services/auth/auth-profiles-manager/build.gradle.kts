plugins {
    id("java")
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    id("jacoco")
    id("maven-publish")
}

group = "com.selling.system.auth.profiles.manager"
version = "1.0.0-SNAPSHOT"

extra["jakartaValidationApiVersion"] = "3.0.2"
extra["springDocOpenAPIVersion"] = "2.3.0"
extra["springCloudVersion"] = "2023.0.0"
extra["lombokVersion"] = "1.18.30"
extra["coreVersion"] = "1.0.0-RELEASE"
extra["authSharedModuleVersion"] = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = project.repositories.mavenLocal().url
    }
}

@Suppress("UnstableApiUsage")
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    finalizedBy(tasks.jacocoTestCoverageVerification)
    reports {
        html.outputLocation = layout.buildDirectory.dir("${project.layout.buildDirectory}/reports/coverage/html/")
    }
}

tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.jacocoTestReport)
    violationRules {
        enabled = true
        rule {
            limit {
                minimum = "0.8".toBigDecimal()
            }
        }
    }
}

jacoco {
    toolVersion = "0.8.11"
    reportsDirectory = layout.buildDirectory.dir("${project.layout.buildDirectory}/reports/")
}

sourceSets {
    main {
        java.srcDirs("src/main/java")
    }
    test {
        java.srcDirs("src/test/java/unit")
        java.srcDirs("src/test/java/integration")
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}


dependencies {
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.postgresql:r2dbc-postgresql:1.0.4.RELEASE")
    implementation("jakarta.validation:jakarta.validation-api:${property("jakartaValidationApiVersion")}")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:${property("springDocOpenAPIVersion")}")
    implementation("org.projectlombok:lombok:${property("lombokVersion")}")
    implementation("com.orderizer.core:orderizer-core:${property("coreVersion")}")
    implementation("com.selling.system.auth.shared.module:auth-shared-module:${property("authSharedModuleVersion")}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok:${property("lombokVersion")}")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

