plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    api(project(":common"))
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    implementation(platform("io.insert-koin:koin-bom:4.0.4"))
    implementation("io.insert-koin:koin-core")
}
