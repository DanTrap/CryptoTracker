plugins {
    alias(libs.plugins.convetion.library)
    alias(libs.plugins.kotlin.serizliation)
}

android.namespace = "com.core.network"

dependencies {
    implementation(libs.bundles.ktor)
    implementation(libs.koin.core)
}
