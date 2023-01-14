pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Chat With Me"
include(":app")
include(":core:model")
include(":core:common")
include(":core:data-test")
include(":core:database")
include(":core:datastore")
include(":core:datastore-test")
include(":core:designsystem")
include(":core:domain")
include(":core:testing")
include(":core:ui")
include(":feature:settings")
include(":core:data")
include(":feature:home")
include(":feature:chat-detail")
include(":feature:status")
include(":feature:calling")
include(":core:network")
include(":benchmark")
include(":logger")
