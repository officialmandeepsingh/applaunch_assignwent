buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(GradlePlugins.Android)
        classpath(GradlePlugins.Kotlin)
        classpath(GradlePlugins.Hilt)
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.2")
        classpath ("android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("com.android.tools.build:gradle:7.2.0")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}