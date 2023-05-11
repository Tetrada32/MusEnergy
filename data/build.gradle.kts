plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    namespace = "com.gahov.data"
}

dependencies {

    dependencies {
        implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

        coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")

        implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.10")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4")

        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

        implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.7")
        implementation("com.squareup.retrofit2:retrofit:2.9.0")

        implementation("com.google.code.gson:gson:2.9.1")

        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
        implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

        implementation("com.google.dagger:hilt-android:2.44")
        kapt("com.google.dagger:hilt-android-compiler:2.44")

        implementation("androidx.room:room-runtime:2.5.1")
        kapt("androidx.room:room-compiler:2.5.1")


        implementation(project(":domain"))
    }
}