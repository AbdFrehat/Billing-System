
val parentModule = project("com.modules.spring:spring-boot:1.0-SNAPSHOT")
group = "com.modules.spring"
version = "1.0-SNAPSHOT"
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("io.projectreactor:reactor-test")
}
