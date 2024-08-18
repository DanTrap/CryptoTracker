plugins {
    alias(libs.plugins.convetion.library)
    alias(libs.plugins.convetion.compose.lib)
}

android.namespace = "com.core.ui"

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(libs.core.ktx)

    implementation(libs.bundles.compose)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.lottie.compose)

    implementation(libs.coil.compose)
}
