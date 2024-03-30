plugins {
    id("java")
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    id("jacoco")
    id("maven-publish")
}

group = "com.orderizer.data.orders.manager"
version = "1.0.0-RELEASE"

extra["springCloudVersion"] = "2023.0.0"
extra["sharedModuleVersion"] = "1.0.0-SNAPSHOT"
extra["lombokVersion"] = "1.18.30"

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
        enabled = false
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
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.cloud:spring-cloud-starter-kubernetes-client-all")
    implementation("com.selling.system.shared.module:shared-module:${property("sharedModuleVersion")}")
    implementation("org.projectlombok:lombok:${property("lombokVersion")}")
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

