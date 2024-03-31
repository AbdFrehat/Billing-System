when (rootProject.name) {
    "services" -> {
        include(":auth:auth-manager")
        include(":auth:auth-users-manager")
        include(":auth:auth-profiles-manager")
        include(":auth:auth-clients-manager")
        include(":auth:auth-shared-module")
    }
    else -> {
        rootProject.name = "auth"
        include(":auth-manager")
        include(":auth-users-manager")
        include(":auth-profiles-manager")
        include(":auth-clients-manager")
        include(":auth-shared-module")
    }
}
