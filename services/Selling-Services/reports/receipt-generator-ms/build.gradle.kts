plugins {
	id("java")
	id("org.springframework.boot")
	id("io.spring.dependency-management")
}

group = "com.selling.system.reports.generate.receipt"
version = "1.0-SNAPSHOT"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("jakarta.validation:jakarta.validation-api:${project.findProperty("jakartaValidationApiVersion")}")
	implementation("net.sf.jasperreports:jasperreports:${project.findProperty("jasperReportsVersion")}")
	implementation("com.github.librepdf:openpdf:${project.findProperty("openPdfVersion")}")
	implementation(project(":models"))
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${project.findProperty("springCloudVersion")}")
	}
}
