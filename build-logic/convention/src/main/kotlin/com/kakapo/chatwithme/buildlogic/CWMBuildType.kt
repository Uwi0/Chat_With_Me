package com.kakapo.chatwithme.buildlogic

@Suppress("unused")
enum class CWMBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
    BENCHMARK(".benchmark")
}
