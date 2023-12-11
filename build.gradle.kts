// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
        kotlin("android") version "1.6.0"
        kotlin("kapt") version "1.6.0"
        id("com.android.application") version "8.1.0"
}

android {
        compileSdkVersion(32)

        defaultConfig {
                applicationId = "com.example.academicagendav1"
                minSdkVersion(16)
                targetSdkVersion(32)
                versionCode = 1
                versionName = "1.0"
                namespace = "http://schemas.android.com/apk/res-auto"
        }

        buildTypes {
                release {
                        isMinifyEnabled = false
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                }
        }

        buildFeatures {
                viewBinding = true
        }

        kotlinOptions {
                jvmTarget = "1.8"
        }
}

dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.10")
        implementation("androidx.core:core-ktx:1.12.0")
        implementation("com.google.android.material:material:1.10.0")
}
