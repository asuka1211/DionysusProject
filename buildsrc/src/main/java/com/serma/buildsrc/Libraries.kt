package com.serma.buildsrc

import org.gradle.api.JavaVersion

object Version {
    const val daggerHiltVersion = "2.35"
    const val glideVersion = "4.11.0"
    const val navVersion = "2.3.4"
    const val composeVersion = "1.0.0-beta05"
}

object AndroidConfig {
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.3"
    const val applicationId = "com.serma.dionysus"
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
    const val versionName = "1.0"
    const val versionCode = 1
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val compose = true
    const val jvmTarget = "1.8"
    const val useIR = true
    val sourceCompatibility = JavaVersion.VERSION_1_8
}

object Libraries {
    const val daggerHilt = "com.google.dagger:hilt-android:${Version.daggerHiltVersion}"
    const val daggerHiltKapt = "com.google.dagger:hilt-compiler:${Version.daggerHiltVersion}"
    const val retrofit2 = "com.squareup.retrofit2:retrofit:2.9.0"
    const val kotlinxSerializationConverter =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
    const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.0"
    const val materialDesign = "com.google.android.material:material:1.2.0"
    const val glide = "com.github.bumptech.glide:glide:${Version.glideVersion}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Version.glideVersion}"
    const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Version.navVersion}"
    const val navUi = "androidx.navigation:navigation-ui-ktx:${Version.navVersion}"
    const val navCompose = "androidx.navigation:navigation-compose:1.0.0-alpha09"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0-alpha01"
    const val composeUi = "androidx.compose.ui:ui:${Version.composeVersion}"
    const val composeMaterial = "androidx.compose.material:material:${Version.composeVersion}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Version.composeVersion}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.0"
    const val composeActivity = "androidx.activity:activity-compose:1.3.0-alpha04"
    const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.4.32"
    const val coreKtx = "androidx.core:core-ktx:1.3.2"
    const val appCompat = "androidx.appcompat:appcompat:1.2.0"
    const val jUnit = "junit:junit:4.13.2"
    const val jUnitAndroid = "androidx.test.ext:junit:1.1.2"
    const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
    const val kotlinxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0"
    const val kotlinxSerializationAdapter ="com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
    const val accompanist = "com.google.accompanist:accompanist-glide:0.7.1"
    const val coroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-experimental-adapter:1.0.0"
}