import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
    id("com.vanniktech.maven.publish") version "0.28.0"
    kotlin("plugin.serialization") version "2.0.20"
}

group = "com.kashif.voyant.navigationcompose"
version = "1.0"

kotlin {
    jvmToolchain(11)
    androidTarget {
        publishLibraryVariants("release")
    }

    jvm()

    wasmJs {
        browser()
        binaries.executable()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "voyant-navigation-compose"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.common)
            api(libs.navigation.compose)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
        }

        jvmMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
        }

    }

    //https://kotlinlang.org/docs/native-objc-interop.html#export-of-kdoc-comments-to-generated-objective-c-headers
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations["main"].compilerOptions.options.freeCompilerArgs.add("-Xexport-kdoc")
    }

}

android {
    namespace = "com.kashif.voyant.navigationcompose"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
    }

    publishing {
        singleVariant("release") {
            withJavadocJar()
            withSourcesJar()
        }

        // For debug variant, we exclude Javadoc and sources to prevent conflicts
        singleVariant("debug") {
            // Exclude Javadoc and sources JARs for debug variant
        }
    }
}

mavenPublishing {
    coordinates(
        groupId = "io.github.kashif-mehmood-km",
        artifactId = "voyant-navigation-compose",
        version = "0.0.9"
    )



    pom {
        name.set("Voyant-Navigation-Compose")
        description.set("Voyant is an extension library for Voyager and Navigation Compose to use native navigation on apple platforms..")
        inceptionYear.set("2024")
        url.set("https://github.com/kashif-e/voyant")

        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }

        developers {
            developer {
                id.set("Kashif-E")
                name.set("Kashif")
                email.set("kashismails@gmail.com")
            }
        }

        scm {
            url.set("https://github.com/kashif-e/voyant")
        }
    }

    // Configure publishing to Maven Central
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    // Enable GPG signing for all publications
    signAllPublications()
}
