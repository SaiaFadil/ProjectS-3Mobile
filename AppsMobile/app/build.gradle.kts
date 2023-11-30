import java.util.regex.Pattern.compile

plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
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
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("com.google.firebase:firebase-messaging:23.3.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("org.mindrot:jbcrypt:0.4")
    // Library untuk upload image
    testImplementation("junit:junit:4.13.2")

    implementation ("com.github.bumptech.glide:glide:4.16.0")

    // Library Retrofit
    implementation ("com.squareup.okhttp3:okhttp:3.4.1")
    implementation ("com.squareup.okhttp3:logging-interceptor:3.4.1")
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.squareup.retrofit2:retrofit:2.8.1")
    implementation("com.squareup.retrofit2:converter-gson:2.8.1")


    //Custom Alert Dialog
    implementation ("com.saadahmedev.popup-dialog:popup-dialog:1.0.5")

    // Library Shimmer
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    // Library OTP GOOGLE
    implementation("com.github.aabhasr1:OtpView:v1.1.2")

    //library kalender
    implementation ("com.applandeo:material-calendar-view:1.7.0")
    implementation ("com.applandeo:material-calendar-view:1.9.0-rc04")
}