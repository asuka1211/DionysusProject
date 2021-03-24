package com.serma.buildsrc

object Version{
    const val daggerVersion = "2.33"
    const val glideVersion = "4.11.0"
    const val navVersion = "2.3.4"
    const val composeVersion = '1.0.0-beta02'
}

object Libraries {
    const val dagger = "com.google.dagger:dagger:${Version.daggerVersion}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Version.daggerVersion}"
    const val retrofit2 = "com.squareup.retrofit2:retrofit:2.9.0"
    const val kotlinxSerializationConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
    const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.0"
    const val materialDesign = "com.google.android.material:material:1.2.0"
    const val glide = "com.github.bumptech.glide:glide:${Version.glideVersion}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Version.glideVersion}"
    const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Version.navVersion}"
    const val navUi = "androidx.navigation:navigation-ui-ktx:${Version.navVersion}"
    const val navCompose = "androidx.navigation:navigation-compose:1.0.0-alpha09"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3"
    const val composeUi = "androidx.compose.ui:ui:${Version.composeVersion}"
    const val composeMaterial = "androidx.compose.material:material:${Version.composeVersion}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Version.composeVersion}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.0"
    const val composeActivity = "androidx.activity:activity-compose:1.3.0-alpha04"
}