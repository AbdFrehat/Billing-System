if (rootProject.name == "services") {
    include(":data:stores:data-stores-manager")
} else if(rootProject.name == "data") {
    include(":stores:data-stores-manager")
} else {
    rootProject.name = "stores"
    include(":data-stores-manager")
}

