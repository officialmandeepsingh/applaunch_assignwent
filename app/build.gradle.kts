plugins {
    id("com.android.application")
    kotlin("android")
//    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = AppConfig.COMPILE_SDK_VERSION
    buildToolsVersion = "30.0.3"
    defaultConfig {
        applicationId = AppConfig.APPLICATION_ID
        minSdk = AppConfig.MIN_SDK_VERSION
        targetSdk = AppConfig.TARGET_SDK_VERSION
        versionCode = AppConfig.VERSION_ID
        versionName = AppConfig.VERSION_NAME
        testInstrumentationRunner = AppConfig.ANDROID_TEST_INSTRUMENTATION
        javaCompileOptions.annotationProcessorOptions.arguments["dagger.hilt.disableModulesHaveInstallInCheck"] =
            "true"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
        buildConfigField("String","BASE_URL", "\"https://api.openweathermap.org\"")
    }

    buildTypes {
        getByName("release") {
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
//        jvmTarget = 11.toString()
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.Core.materialDesign)
    implementation(Dependencies.Core.timber)
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.gson)
    implementation(Dependencies.Core.coroutine)
    implementation(Dependencies.Core.navigationUi)
    implementation(Dependencies.Core.coil)
    implementation(Dependencies.Core.navigationFragment)
    implementation(Dependencies.Core.ktxViewModel)
    implementation(Dependencies.Core.ktxLiveData)
    implementation(Dependencies.Core.ktxLifeCycleScope)
    implementation(Dependencies.AndroidX.fragmentKtx)
    implementation (Dependencies.Network.googleGson)
    implementation (Dependencies.Network.httpLogging)
    implementation (Dependencies.AndroidX.lifecycleExtensions)
    implementation("androidx.annotation:annotation-experimental:1.3.0")
    kapt(Dependencies.Kotlin.kotlinxMetaData)
    implementation(Dependencies.Hilt.hilt)
    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.room)
    annotationProcessor(Dependencies.Room.compiler)
    kapt(Dependencies.Room.compiler)
    implementation(Dependencies.Hilt.lifecycle)
    kapt(Dependencies.Hilt.compiler)
    kapt(Dependencies.Hilt.hiltCompiler)
    implementation(Dependencies.Core.leakCanary)
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

}
kapt {
    correctErrorTypes = true
}