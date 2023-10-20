
plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "com.sales"
version = "0.0.1-SNAPSHOT"


dependencies {
//    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
//    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb-reactive'
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    testImplementation ("io.projectreactor:reactor-test")
}


