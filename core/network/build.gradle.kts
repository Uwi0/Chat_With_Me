plugins {
    id("chatwithme.android.library")
    id("chatwithme.android.library.jacoco")
    id("chatwithme.android.hilt")
}

android {
    namespace = "com.kakapo.network"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    testImplementation(project(":core:testing"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
}