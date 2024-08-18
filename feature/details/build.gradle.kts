plugins {
    alias(libs.plugins.convetion.feature)
}

android.namespace = "com.feature.details"

dependencies {
    implementation(project(":core:common"))
    implementation(libs.coil.compose)
}
