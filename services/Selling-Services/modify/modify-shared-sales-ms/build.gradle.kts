plugins {
	id("java")
	id("org.springframework.boot")
	id("io.spring.dependency-management")
}

group = "com.selling.system.modify.sha.sales"
version = "1.0.0-SNAPSHOT"


dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation(project(":shared:shared-module"))
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
}



