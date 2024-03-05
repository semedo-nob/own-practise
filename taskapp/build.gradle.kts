import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("libs.gradle" )// Update to the latest version of Android Gradle Plugin
        classpath ("libs.kotlin.gradle.plugin") // Update to the latest version of Kotlin Gradle Plugin
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir // Delete the root project's build directory
}

tasks.withType(KotlinCompile) {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
