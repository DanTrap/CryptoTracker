plugins {
    alias(libs.plugins.convetion.library)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.serizliation)
}

android.namespace = "com.core.database"

dependencies {
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
    implementation(libs.koin.android)
    implementation(libs.kotlinx.serialization.json)
}
