import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

//	Proerties 표준함수명이 Java > kotlin 으로 넘어오면서 변함.
//.inputStream() 부분도 표준 함수명이 변해 kotlin 환경에서 사용하시는분들은 이렇게 참고해서 사용하시면 됩니다.
val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())


android {

    defaultConfig {
        minSdk = 24
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "NATIVE_KEY",
            properties.getProperty("native_key")
        )
        manifestPlaceholders["NATIVE_KEY"] = properties.getProperty("manifest_native_key")
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
    buildFeatures{
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    // retrofit

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Kakao SDK 추가
    implementation("com.kakao.sdk:v2-all:2.20.1")
    implementation("com.kakao.sdk:v2-user:2.20.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}