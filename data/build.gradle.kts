plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlinx-serialization")
    id("com.google.dagger.hilt.android") version "2.55"
    id("com.google.devtools.ksp") version "2.1.10-1.0.30"
}

android {
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    namespace = "com.gahov.data"
}

dependencies {

    dependencies {
        implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

        coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.4")

        implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.10")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.10.1")

        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

        implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.9")
        implementation("com.squareup.retrofit2:retrofit:2.9.0")

        implementation("com.google.code.gson:gson:2.11.0")

        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
        implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

        implementation("com.google.dagger:hilt-android:2.55")
        ksp("com.google.dagger:hilt-android-compiler:2.55")

        implementation("androidx.room:room-runtime:2.7.0-beta01")
        ksp("androidx.room:room-compiler:2.7.0-beta01")

        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
        implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

        implementation(project(":domain"))
    }
}