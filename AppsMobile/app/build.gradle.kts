plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.usingpreferences"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.usingpreferences"
        minSdk = 24
        targetSdk = 33
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
}

dependencies {
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")
    implementation("com.google.android.gms:play-services-auth:19.2.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation ("org.mockito:mockito-core:3.9.0")

    testImplementation("junit:junit:4.13.2")


    // Library Retrofit
    implementation ("com.squareup.okhttp3:okhttp:3.4.1")
    implementation ("com.squareup.okhttp3:logging-interceptor:3.4.1")
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.squareup.retrofit2:retrofit:2.8.1")
    implementation("com.squareup.retrofit2:converter-gson:2.8.1")
}