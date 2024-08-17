plugins {
    alias(libs.plugins.convetion.feature)
}

android.namespace = "com.feature.home"

dependencies {
    implementation(project(":core:common"))
    implementation(libs.material.icons.extended)
}
