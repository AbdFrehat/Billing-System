plugins {
	id("java")
	id("org.springframework.boot")
	id("io.spring.dependency-management")
}

group = "com.example"
version = "1.0-SNAPSHOT"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("jakarta.validation:jakarta.validation-api:${project.findProperty("jakartaValidationApiVersion")}")
	implementation("org.projectlombok:lombok:${project.findProperty("lombokVersion")}")
	implementation("net.sf.jasperreports:jasperreports:6.20.1")
	implementation("com.github.librepdf:openpdf:1.3.22")
	implementation(project(":models"))
	annotationProcessor("org.projectlombok:lombok:${project.findProperty("lombokVersion")}")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
}
