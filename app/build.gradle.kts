plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gahov.musenergy"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    namespace = "com.gahov.musenergy"

    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra.get("kotlinVersion") as String}")

    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.core:core-ktx:1.12.0")

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.11.0")
    implementation("dev.chrisbanes.insetter:insetter-dbx:0.6.1")
    implementation("dev.chrisbanes.insetter:insetter:0.6.1")

    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    implementation("com.google.dagger:hilt-android:2.47")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    kapt("com.google.dagger:hilt-android-compiler:2.47")

    implementation("com.google.android.exoplayer:exoplayer:2.19.1")

    implementation("androidx.startup:startup-runtime:1.1.1")

    implementation("io.coil-kt:coil:2.2.2")

    implementation("com.airbnb.android:lottie:5.2.0")

    implementation(project(":data"))
    implementation(project(":domain"))

}