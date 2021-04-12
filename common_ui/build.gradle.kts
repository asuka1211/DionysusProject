import com.serma.buildsrc.*

plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdkVersion(AndroidConfig.compileSdkVersion)
    buildToolsVersion(AndroidConfig.buildToolsVersion)

    defaultConfig {
        minSdkVersion(AndroidConfig.minSdkVersion)
        targetSdkVersion(AndroidConfig.targetSdkVersion)
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName

        testInstrumentationRunner = AndroidConfig.testInstrumentationRunner
    }

    buildTypes {
        release {
            minifyEnabled(false)
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
    implementation(Libraries.composeMaterial)
    implementation(Libraries.composeUiTooling)
    implementation(Libraries.lifecycle)
    implementation(Libraries.composeActivity)
    testImplementation(Libraries.jUnit)
    androidTestImplementation(Libraries.jUnitAndroid)
    androidTestImplementation(Libraries.espresso)
    implementation(Libraries.kotlinStd)
}