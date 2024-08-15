plugins {
    alias(libs.plugins.convetion.feature)
}

android.namespace = "com.feature.settings"

dependencies {
    implementation(project(":core:common"))
    implementation(libs.koin.compose)
}
