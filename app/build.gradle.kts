plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.weathertask"
    compileSdk = 35

    signingConfigs {
        create("release") {
            storeFile = file("/Weather123.jks")
            storePassword = "Weather123"
            keyAlias = "Weather123"
            keyPassword = "Weather123"
        }
    }

    defaultConfig {
        applicationId = "com.example.weathertask"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            applicationVariants.all { variant ->
               variant.outputs.forEach { output ->
                    if (output is com.android.build.gradle.internal.api.BaseVariantOutputImpl) {
                        output.outputFileName = "weather_app.apk"
                    }
                }
                return@release
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation (project(":core"))
    implementation (project(":data"))
    implementation (project(":domain"))
    implementation (project(":features:search_cities"))
    implementation (project(":features:current_weather"))
    implementation (project(":features:forecast"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // Google Maps SDK for Android
    implementation(libs.places)

    implementation (libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.androidx.hilt.compiler)
    implementation (libs.androidx.hilt.navigation.compose)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)



    implementation (libs.androidx.navigation.compose)
    implementation (libs.accompanist.navigation.animation)

    //chucker
    debugImplementation(libs.library)
    releaseImplementation(libs.library.no.op)
}