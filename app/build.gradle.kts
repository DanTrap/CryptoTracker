plugins {
    alias(libs.plugins.convetion.application)
    alias(libs.plugins.convetion.compose.app)
}

android {
    namespace = "com.dantrap.cryptotracker"
    defaultConfig.applicationId = "com.dantrap.cryptotracker"
    androidResources.generateLocaleConfig = true
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":core:mvi"))
    implementation(project(":core:database"))
    implementation(project(":core:datastore"))
    implementation(project(":feature:home"))
    implementation(project(":feature:settings"))

    implementation(libs.activity.compose)

    implementation(libs.compose.navigation)

    implementation(libs.bundles.compose)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.material.icons.extended)

    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    implementation(libs.orbit.viewmodel)
    implementation(libs.orbit.compose)
}
