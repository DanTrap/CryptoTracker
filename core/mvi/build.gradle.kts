plugins {
    alias(libs.plugins.convetion.library)
}

android.namespace = "com.core.mvi"

dependencies {
    api(libs.orbit.viewmodel)
}
