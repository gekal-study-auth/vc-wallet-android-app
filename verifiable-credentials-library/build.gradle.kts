import java.net.URI

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.diffplug.spotless")
}

android {
    namespace = "org.idp.wallet.verifiable_credentials_library"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}


dependencies {

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("id.walt:waltid-sd-jwt-jvm:1.2306191408.0")

    //JSON
    implementation("com.beust:klaxon:5.6")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    //JSONPath refer to https://identity.foundation/presentation-exchange/spec/v2.0.0/#jsonpath-implementations
    implementation("com.nfeld.jsonpathkt:jsonpathkt:2.0.1")


    //jose
    implementation("com.nimbusds:nimbus-jose-jwt:9.37.3")

    testImplementation("androidx.test:core-ktx:1.5.0")
    testImplementation("org.robolectric:robolectric:4.11.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

spotless { // if you are using build.gradle.kts, instead of 'spotless {' use:
    // configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    kotlin {
        target("/src/**/*.kt")
        ktfmt()
    }
}