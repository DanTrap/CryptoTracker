plugins {
    alias(libs.plugins.convetion.library)
}

android.namespace = "com.core.datastore"

dependencies {
    implementation(libs.datastore.preferences)
    implementation(libs.koin.android)
}
