plugins {
	id("java")
	id("org.springframework.boot")
	id("io.spring.dependency-management")
}

group = "com.sales.fetch"
version = "1.0-SNAPSHOT"

extra["springCloudVersion"] = "2022.0.4"

dependencies {
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${project.findProperty("springCloudVersion")}")
	}
}


