plugins {
    id("java")
    id("jacoco")
}
group = "com.selling.parent"
version = "1.0-SNAPSHOT"

allprojects {
    apply(plugin = "java")
    apply(plugin = "jacoco")

    repositories {
        mavenCentral()
    }
    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    tasks.test {
        useJUnitPlatform()
        finalizedBy(tasks.jacocoTestReport)
    }

    tasks.jacocoTestReport {
        dependsOn(tasks.test)
        finalizedBy(tasks.jacocoTestCoverageVerification)
        reports {
            html.outputLocation = layout.buildDirectory.dir("$buildDir/reports/coverage/html/")
        }
    }

    tasks.jacocoTestCoverageVerification {
        dependsOn(tasks.jacocoTestReport)
        violationRules {
            rule {
                limit {
                    minimum = "0.8".toBigDecimal()
                }
            }
        }
    }

    jacoco {
        toolVersion = "0.8.11"
        reportsDirectory = layout.buildDirectory.dir("$buildDir/reports/")
    }

    dependencies {
        implementation("org.projectlombok:lombok:${project.findProperty("lombokVersion")}")
        annotationProcessor("org.projectlombok:lombok:${project.findProperty("lombokVersion")}")
    }

    sourceSets {
        main {
            java.srcDirs("src/main/java")
        }
        test {
            java.srcDirs("src/test/java")
        }
    }

}






