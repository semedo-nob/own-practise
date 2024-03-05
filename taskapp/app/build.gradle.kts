plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.task.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.task.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    dependencies {

        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.material)
        implementation(libs.androidx.activity)
        implementation(libs.androidx.constraintlayout)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        implementation (libs.androidx.core.ktx)
        implementation (libs.androidx.appcompat)
        implementation (libs.material)
        implementation ("libs.androidx.activity.ktx")
        implementation( libs.androidx.constraintlayout)
        implementation ("libs.lifecycle.extensions")

        // Add Mixpanel SDK dependency
        implementation ("libs.mixpanel.android")
        implementation ("com.mixpanel.android:mixpanel-android:")

        // Add any other dependencies you may need

        testImplementation (libs.junit)
        androidTestImplementation (libs.androidx.junit)
        androidTestImplementation (libs.androidx.espresso.core)
    }

}