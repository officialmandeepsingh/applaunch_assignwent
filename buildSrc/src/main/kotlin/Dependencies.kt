object Dependencies {
    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val kotlinxMetaData =
            "org.jetbrains.kotlinx:kotlinx-metadata-jvm:${Versions.kotlinxMetaData}"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
        const val lifecycleExtensions =
            "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    }

    object Core {
        const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
        const val coil = "io.coil-kt:coil:${Versions.coil}"
        const val coroutine =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
        const val ktxViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.KTXLifecycle}"
        const val ktxLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.KTXLifecycle}"
        const val ktxLifeCycleScope =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.KTXLifecycle}"
        const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val googleGson = "com.google.code.gson:gson:${Versions.retrofit}"
        const val httpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.httpLogging}"
    }

    object Hilt {
        const val hilt = "com.google.dagger:hilt-android:${Versions.Hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.Hilt}"
        const val lifecycle = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.Hilt_old}"
        const val compiler = "androidx.hilt:hilt-compiler:${Versions.Hilt_old}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.junit}"
        const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.roomDatabase}"
        const val compiler = "androidx.room:room-compiler:${Versions.roomDatabase}"
        const val room = "androidx.room:room-ktx:${Versions.roomDatabase}"
    }

}