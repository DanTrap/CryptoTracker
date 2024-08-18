plugins {
    alias(libs.plugins.convetion.library)
    alias(libs.plugins.kotlin.serizliation)
    alias(libs.plugins.convetion.api.key.provider)
}

android {
    namespace = "com.core.network"
    buildFeatures.buildConfig = true
}

dependencies {
    implementation(libs.bundles.ktor)
    implementation(libs.koin.core)
}
