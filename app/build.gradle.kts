import com.serma.buildsrc.AndroidConfig
import com.serma.buildsrc.Libraries
import com.serma.buildsrc.Version

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.4.30"
}

android {
    compileSdk = AndroidConfig.compileSdkVersion
    buildToolsVersion = AndroidConfig.buildToolsVersion

    defaultConfig {
        applicationId = AndroidConfig.applicationId
        minSdk = AndroidConfig.minSdkVersion
        targetSdk = AndroidConfig.targetSdkVersion
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName

        testInstrumentationRunner = AndroidConfig.testInstrumentationRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = AndroidConfig.jvmTarget
        useIR = AndroidConfig.useIR
    }
    buildFeatures {
        compose = AndroidConfig.compose
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.composeVersion
    }
}

repositories {
    google()
    mavenCentral()
    jcenter()
    // Warning: this repository is going to shut down soon
}

dependencies {

    implementation(Libraries.coreKtx)
    implementation(Libraries.appCompat)
    implementation(Libraries.materialDesign)
    implementation(Libraries.composeUi)
    implementation(Libraries.retrofit2)
    implementation(Libraries.coroutines)
    implementation(Libraries.coroutinesAdapter)
    implementation(Libraries.kotlinxSerializationAdapter)
    implementation(Libraries.accompanist)
    implementation(Libraries.coroutinesAndroid)
    implementation(Libraries.composeMaterial)
    implementation(Libraries.composeUiTooling)
    implementation(Libraries.lifecycle)
    implementation(Libraries.composeActivity)
    testImplementation(Libraries.jUnit)
    androidTestImplementation(Libraries.jUnitAndroid)
    androidTestImplementation(Libraries.espresso)
    implementation(Libraries.kotlinStd)
    implementation(Libraries.navCompose)
    implementation(Libraries.kotlinxSerialization)
    kapt(Libraries.daggerHiltKapt)
    implementation(Libraries.daggerHilt)
    implementation(Libraries.daggerHiltCompose)
}