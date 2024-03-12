import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

// 선언 및 키값을 불러오기
val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

android {
    namespace = "com.example.booksearchapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.booksearchapp"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        android.buildFeatures.buildConfig = true

        buildConfigField("String", "KAKAO_API_KEY", properties["bookApiKey"] as String)
        manifestPlaceholders["KAKAO_API_KEY"] = properties["bookApiKey"] as String
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("Boolean", "DEBUG_MODE", "false")
        }
        debug {
            buildConfigField("Boolean", "DEBUG_MODE", "false")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    viewBinding {
        enable = true
    }
    buildFeatures {
        buildConfig = true
    }
    // Allow references to generated code
    kapt {
        correctErrorTypes = true
    }

//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11
//    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.fragment:fragment-ktx:1.6.1")


    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")


    //Moshi
    implementation("com.squareup.moshi:moshi:1.14.0")


    //OkHttp
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))

    // define any required OkHttp artifacts without version
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    //viewModelScope
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.2")

    //Coil
    implementation("io.coil-kt:coil:2.4.0")

    //Navigation
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")


    //Room
    implementation("androidx.room:room-runtime:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")
    annotationProcessor("androidx.room:room-compiler:2.5.0")
    ksp("androidx.room:room-compiler:2.5.0")
    implementation("androidx.room:room-paging:2.5.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    implementation("androidx.datastore:datastore-preferences:1.0.0")

    implementation("androidx.paging:paging-runtime:3.1.1")


    //work manager
    val work_version = "2.8.0"
    // Kotlin + coroutines
    implementation("androidx.work:work-runtime-ktx:$work_version")

    //hilt
    implementation("com.google.dagger:hilt-android:2.48")
    ksp("com.google.dagger:hilt-android-compiler:2.48")
    ksp("com.google.dagger:dagger-compiler:2.48") // Dagger compiler
    ksp("com.google.dagger:hilt-compiler:2.48")


    //ViewModel delegate
    implementation("androidx.activity:activity-ktx:1.7.0")
    implementation("androidx.fragment:fragment-ktx:1.6.1")


    //hilt extension
    implementation("androidx.hilt:hilt-work:1.0.0")
    ksp("androidx.hilt:hilt-compiler:1.0.0")


    //text
    testImplementation("androidx.test.ext:truth:1.5.0")
    testImplementation("androidx.test:runner:1.5.2")

}