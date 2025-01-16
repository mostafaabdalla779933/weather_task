// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    extra["signing"] = mapOf(
        "storePassword" to "Weather123",
        "keyAlias" to "Weather123",
        "keyPassword" to "Weather123"
    )
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    id ("com.google.dagger.hilt.android") version ("2.51.1") apply false

}