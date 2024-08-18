plugins {
    `kotlin-dsl`
}

group = "com.build.logic"

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.detekt.gradle.plugin)

    compileOnly(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "convention.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidComposeApplication") {
            id = "convention.android.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "convention.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidComposeLibrary") {
            id = "convention.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("featureAndroidLibrary") {
            id = "convention.android.feature.library"
            implementationClass = "FeatureConventionPlugin"
        }
        register("detekt") {
            id = "convention.detekt"
            implementationClass = "DetektConventionPlugin"
        }
        register("apiKeyProvider") {
            id = "convention.api.key.provider"
            implementationClass = "ApiKeyProviderConventionPlugin"
        }
    }
}
