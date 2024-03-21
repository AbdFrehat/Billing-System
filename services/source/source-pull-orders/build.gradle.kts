plugins {
	id("java")
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
	id("jacoco")
	id("maven-publish")
}

group = "com.selling.system.source.pull.sales"
version = "1.0.0-SNAPSHOT"

extra["lombokVersion"] = "1.18.30"
extra["springDocOpenAPIVersion"] = "2.3.0"
extra["springCloudVersion"] = "2023.0.0"
extra["sharedModuleVersion"] = "1.0.0-SNAPSHOT"
extra["sourceOrdersRandomGeneratorVersion"] = "1.0.0-SNAPSHOT"

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
	implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:${property("springDocOpenAPIVersion")}")
	implementation("com.selling.system.shared.module:shared-module:${property("sharedModuleVersion")}")
	implementation("com.orderizer.source.random.generator.orders:source-orders-random-generator:${property("sourceOrdersRandomGeneratorVersion")}")
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