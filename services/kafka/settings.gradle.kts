apply(from = "orders/settings.gradle.kts")

if(rootProject.name != "services") {
    rootProject.name = "kafka"
}