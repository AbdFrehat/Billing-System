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
	implementation("com.github.librepdf:openpdf:${project.findProperty("librepdfOpenpdfVersion")}")
	implementation("com.lowagie:itext:${project.findProperty("lowagieItext")}")
	implementation("org.mozilla:rhino:${project.findProperty("mozillaRhino")}")
	implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:${project.findProperty("springDocOpenAPIVersion")}")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv:${project.findProperty("fasterxmlJacksonVersion")}")
	implementation(project(":shared:shared-module"))
	implementation(project(":export:export-data-shared-module"))
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${project.findProperty("springCloudVersion")}")
	}
}
