plugins {
    java
}

group = "com.selling.parent"
version = "1.0-SNAPSHOT"


allprojects {
    apply(plugin = "java")
    repositories {
        mavenCentral()
    }
}
repositories {
    mavenCentral()
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
}

tasks.test {
    useJUnitPlatform()
}



