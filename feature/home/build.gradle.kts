plugins {
    id("chatwithme.android.feature")
    id("chatwithme.android.library.compose")
    id("chatwithme.android.library.jacoco")
}

android {
    namespace = "com.kakapo.home"
}

dependencies {

    implementation(project(":feature:chat-detail"))
}