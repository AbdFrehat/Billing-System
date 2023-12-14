plugins {
	id("java")
	id("org.springframework.boot")
	id("io.spring.dependency-management")
}

group = "com.selling.system.export.data.pdf"
version = "1.0.0-SNAPSHOT"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("jakarta.validation:jakarta.validation-api:${project.findProperty("jakartaValidationApiVersion")}")
	implementation("net.sf.jasperreports:jasperreports:${project.findProperty("jasperReportsVersion")}")
	implementation("com.github.librepdf:openpdf:1.3.30")
	implementation("com.lowagie:itext:2.1.7")
	implementation("org.mozilla:rhino:1.7.14")
	implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:${project.findProperty("springDocOpenAPIVersion")}")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv:${project.findProperty("fasterxmlJacksonVersion")}")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation(project(":shared:shared-module"))
	implementation(project(":export:export-data-shared-module"))
	testImplementation("io.projectreactor:reactor-test")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${project.findProperty("springCloudVersion")}")
	}
}
