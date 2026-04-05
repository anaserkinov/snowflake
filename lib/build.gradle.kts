import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.mavenPublish)
    alias(libs.plugins.ktlint)
}

kotlin {
    android {
        namespace = "me.anasmusa.snowflake"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Snowflake"
            isStatic = true
        }
    }
    
    jvm("desktop")
    
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        outputModuleName = "snowflake"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "snowflake.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static(rootDirPath)
                    static(projectDirPath)
                }
            }
        }
        binaries.executable()
    }

    sourceSets {
        val desktopMain by getting
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

dependencies {
    androidRuntimeClasspath(compose.uiTooling)
}

allprojects {
    group = "me.anasmusa"
    version = (project.findProperty("VERSION_NAME") as String?) ?: "1.0.0-SNAPSHOT"
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(artifactId = "snowflake")

    pom {
        name = "Snowflake"
        description = "Animated snow effect"
        inceptionYear = "2025"
        url = "https://github.com/anaserkinov/snowflake/"
        licenses {
            license {
                name = "GPL-2.0 license"
                url = "https://github.com/anaserkinov/snowflake?tab=GPL-2.0-1-ov-file"
                distribution = "https://github.com/anaserkinov/snowflake?tab=GPL-2.0-1-ov-file"
            }
        }
        developers {
            developer {
                name = "Anas"
                email = "anaserkinjonov@gmail.com"
                url = "https://github.com/anaserkinov/"
            }
        }
        scm {
            url = "https://github.com/anaserkinov/snowflake/"
            connection = "scm:git:git://github.com/anaserkinov/snowflake.git"
            developerConnection = "scm:git:ssh://git@github.com/anaserkinov/snowflake.git"
        }
    }
}

