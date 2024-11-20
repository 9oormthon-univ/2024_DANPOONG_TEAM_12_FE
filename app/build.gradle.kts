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
    namespace = "com.example.waytogo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.waytogo"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

        buildConfigField(
            "String",
            "NATIVE_KEY",
            properties.getProperty("native_key")
        )
        manifestPlaceholders["NATIVE_KEY"] = properties.getProperty("manifest_native_key")
    buildFeatures {
        viewBinding = true
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
    implementation("com.kakao.maps.open:android:2.9.5")

    // Material Design
    implementation("com.google.android.material:material:1.9.0")

    // viewPager2
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    // Fragment
    implementation("androidx.fragment:fragment:1.8.3")
    implementation("androidx.fragment:fragment-ktx:1.8.3")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("androidx.navigation:navigation-fragment-ktx:2.8.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.4")

}