plugins {
    kotlin("android")
    kotlin("kapt")
    id("com.android.library")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android") version "2.51.1"
//    id("com.google.devtools.ksp") version "2.1.10-1.0.30"
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
    namespace = "com.gahov.domain"
}
dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.4")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.10")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
}