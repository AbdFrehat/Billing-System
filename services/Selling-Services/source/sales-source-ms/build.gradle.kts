plugins {
	id("java")
	id("org.springframework.boot")
	id("io.spring.dependency-management")
}

group = "com.sale.source"
version = "1.0.0-SNAPSHOT"



dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation(project(":models"))
	implementation(project(":source:sales-random-generator"))
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
}
