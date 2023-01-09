import com.kakapo.chatwithme.buildlogic.CWMBuildType

plugins {
    id("chatwithme.android.application")
    id("chatwithme.android.application.compose")
    id("chatwithme.android.application.jacoco")
    id("chatwithme.android.hilt")
    id("jacoco")
}

android {
    namespace = "com.kakapo.chatwithme"
    defaultConfig {
        applicationId = "com.kakapo.chatwithme"
        versionCode = 1
        versionName  = "1.0"

        testInstrumentationRunner  = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val debug by getting {
            isMinifyEnabled = false
            applicationIdSuffix = CWMBuildType.DEBUG.applicationIdSuffix
        }
        val release by getting {
            isMinifyEnabled = true
            applicationIdSuffix = CWMBuildType.RELEASE.applicationIdSuffix
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

        }
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":feature:home"))

    implementation(libs.accompanist.systemuicontroller)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.lifecycle.runtimeCompose)

    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.runtime.tracing)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.window.manager)
    implementation(libs.androidx.profileinstaller)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)

    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.accompanist.testharness)
    debugImplementation(libs.androidx.compose.ui.testManifest)
}