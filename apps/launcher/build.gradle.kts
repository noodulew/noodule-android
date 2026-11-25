plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "org.noodule.launcher"
    compileSdk = 34
    ndkVersion = "26.1.10909125"

    defaultConfig {
        applicationId = "org.noodule"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "0.1a"

        ndk {
            // All four ABIs, nothing excluded
            abiFilters += listOf("arm64-v8a", "armeabi-v7a", "x86", "x86_64")
        }

        externalNativeBuild {
            cmake {
                cppFlags += "-std=c++17"
            }
        }
    }

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    sourceSets["main"].jniLibs.srcDirs("src/main/jniLibs")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
}

// Build Rust for all four ABIs into src/main/jniLibs/<abi>/libnodule_core.so
val cargoNdk by tasks.registering(Exec::class) {
    workingDir = rootProject.file("rust")
    val outDir = file("src/main/jniLibs")
    outDir.mkdirs()
    commandLine(
        "cargo", "ndk",
        "-t", "arm64-v8a",
        "-t", "armeabi-v7a",
        "-t", "x86",
        "-t", "x86_64",
        "-o", outDir.absolutePath,
        "build", "--release", "-p", "core"
    )
}

tasks.named("preBuild") { dependsOn(cargoNdk) }