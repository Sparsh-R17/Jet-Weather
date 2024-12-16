import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.jetapp.jet_weather"
    compileSdk = 34

    buildFeatures {
        compose = true
        buildConfig =  true
    }

    defaultConfig {
        applicationId = "com.jetapp.jet_weather"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val secretFiles = rootProject.file("local.properties")
        val properties = Properties()
        properties.load(secretFiles.inputStream())


        val apiKey = properties.getProperty("api_key") ?: ""


        //for accessing the property using BuildConfig
        buildConfigField(
            type = "String",
            name = "API_KEY",
            value = apiKey
        )
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
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

     //Room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.room.compiler)

    //Dagger-Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    //To use Kotlin annotation processing tool (kapt) MUST HAVE!
    ksp(libs.room.compiler)
    implementation(libs.androidx.room.ktx)

    //Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    //Coroutines Lifecycle Scope
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    //lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)

    //Coil
    implementation(libs.coil.compose)

    //Retrofit
    implementation(libs.retrofit)
//
//    //Json Converter
    implementation(libs.converter.gson)

    //OkHttp
    implementation(libs.okhttp)
}

kapt{
    correctErrorTypes = true
}