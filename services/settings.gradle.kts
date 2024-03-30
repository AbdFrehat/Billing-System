rootProject.name = "services"

apply(from = "data/settings.gradle.kts")
apply(from = "export/settings.gradle.kts")
apply(from = "modify/settings.gradle.kts")
apply(from = "auth/settings.gradle.kts")
apply(from = "source/settings.gradle.kts")
apply(from = "reports/settings.gradle.kts")
apply(from = "kafka/settings.gradle.kts")

include(":shared:shared-module")






