buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha15")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.33-beta")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts.ktx.kts.kts files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}