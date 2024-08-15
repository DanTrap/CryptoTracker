plugins {
    alias(libs.plugins.convetion.library)
}

android.namespace = "com.core.data"

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
    implementation(project(":core:common"))

    implementation(libs.appcompat)

    implementation(libs.koin.android)

    implementation(libs.datastore.preferences)
}
