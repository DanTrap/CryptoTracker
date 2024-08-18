plugins {
    alias(libs.plugins.convetion.feature)
}

android.namespace = "com.feature.home"

dependencies {
    implementation(project(":core:common"))
    implementation(libs.coil.compose)
    testImplementation(libs.mockk)
    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.orbit.test)
}
