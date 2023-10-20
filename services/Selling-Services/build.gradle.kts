plugins {
    id("java")
}
group = "com.selling.parent"
version = "1.0-SNAPSHOT"

subprojects {
    apply(plugin = "java")
    repositories {
        mavenCentral()
    }
    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    tasks.test {
        useJUnitPlatform()
    }
}





