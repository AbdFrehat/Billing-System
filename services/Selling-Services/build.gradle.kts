plugins {
    id("java")
}
group = "com.selling.parent"
version = "1.0-SNAPSHOT"

allprojects {
    apply(plugin = "java")
    repositories {
        mavenCentral()
    }
    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    tasks.test {
        useJUnit()
    }
    dependencies {
        implementation("org.projectlombok:lombok:${project.findProperty("lombokVersion")}")
        annotationProcessor("org.projectlombok:lombok:${project.findProperty("lombokVersion")}")
    }
}




