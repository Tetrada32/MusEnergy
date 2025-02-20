buildscript {
    val kotlinVersion by extra("2.1.10")

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.8.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.8.7")
    }
}

plugins {
    val kotlinVersion = "2.1.10"

    id("com.google.dagger.hilt.android") version "2.55" apply false
    id("org.jetbrains.kotlin.android") version kotlinVersion apply false
    id("org.jetbrains.kotlin.kapt") version "2.1.10" apply false
    id("com.google.devtools.ksp") version "2.1.10-1.0.30" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(layout.buildDirectory)
}
