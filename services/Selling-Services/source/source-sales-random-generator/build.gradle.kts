plugins {
    id("java")
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    id("jacoco")
    id("maven-publish")
}

group = "com.selling.system.source.random.generator.sales"
version = "1.0.0-SNAPSHOT"

extra["fasterxmlJacksonVersion"] = "2.15.3"
extra["lombokVersion"] = "1.18.30"
extra["sharedModuleVersion"] = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = project.repositories.mavenLocal().url
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
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
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("com.fasterxml.jackson.core:jackson-core:${property("fasterxmlJacksonVersion")}")
    implementation("com.fasterxml.jackson.core:jackson-databind:${property("fasterxmlJacksonVersion")}")
    implementation("com.selling.system.shared.module:shared-module:${property("sharedModuleVersion")}")
    implementation("org.projectlombok:lombok:${property("lombokVersion")}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    annotationProcessor("org.projectlombok:lombok:${property("lombokVersion")}")
}


