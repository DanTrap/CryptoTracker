plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.serizliation) apply false

    alias(libs.plugins.convetion.feature) apply false
    alias(libs.plugins.convetion.library) apply false
    alias(libs.plugins.convetion.application) apply false
    alias(libs.plugins.convetion.compose.lib) apply false
    alias(libs.plugins.convetion.compose.app) apply false

    alias(libs.plugins.convetion.detekt)
}
