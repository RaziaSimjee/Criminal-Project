plugins {
    alias(libs.plugins.android.application)
    id("com.google.devtools.ksp") version "2.3.6"
    id ("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.criminalproject"
    compileSdk {
        version = release(37)
    }
    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.example.criminalproject"
        minSdk = 35
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.11.0")
    implementation("androidx.fragment:fragment-ktx:1.9.1")
    implementation("androidx.recyclerview:recyclerview:1.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.11.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.11.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.11.0")

    val roomVersion = "2.8.4"

    implementation("androidx.room:room-runtime:$roomVersion") // provides the database functionality.
    implementation("androidx.room:room-ktx:$roomVersion")   // coroutines + Flow support
    ksp("androidx.room:room-compiler:$roomVersion")         // generates the code
    implementation("androidx.navigation:navigation-fragment-ktx:2.10.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.10.2")
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}