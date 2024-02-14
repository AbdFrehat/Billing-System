plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("maven-publish")
}

group = "com.selling.system.shared.module"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        setUrl("file://Users/abdfrehat/.gradle/caches/modules-2/files-2.1")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:${project.findProperty("springDocOpenAPIVersion")}")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:${project.findProperty("fasterxmlJacksonVersion")}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            setUrl("file://Users/abdfrehat/.gradle/caches/modules-2/files-2.1")
        }
    }
}



