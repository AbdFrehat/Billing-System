plugins {
	id("java")
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
	id("jacoco")
	id("maven-publish")
}
group = "com.selling.system.export.data.json"
version = "1.0.0-SNAPSHOT"

extra["jakartaValidationApiVersion"] = "3.0.2"
extra["springDocOpenAPIVersion"] = "2.3.0"
extra["springCloudVersion"] = "2023.0.0"
extra["lombokVersion"] = "1.18.30"
extra["coreVersion"] = "1.0.0-RELEASE"
extra["exportDataSharedModuleVersion"] = "1.0.0-SNAPSHOT"

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
	implementation("org.springframework.cloud:spring-cloud-starter-kubernetes-client-all")
	implementation("jakarta.validation:jakarta.validation-api:${property("jakartaValidationApiVersion")}")
	implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:${property("springDocOpenAPIVersion")}")
	implementation("com.orderizer.core:orderizer-core:${property("coreVersion")}")
	implementation("com.selling.system.export.shared:export-data-shared-module:${property("exportDataSharedModuleVersion")}")
	implementation("org.projectlombok:lombok:${property("lombokVersion")}")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}
