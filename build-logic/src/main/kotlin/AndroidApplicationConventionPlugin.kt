import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.buildlogic.configureKotlinAndroid
import com.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.plugins.android.application.get().pluginId)
            apply(libs.plugins.kotlin.android.get().pluginId)
        }

        extensions.configure<BaseAppModuleExtension> {
            configureKotlinAndroid(this)

            defaultConfig {
                targetSdk = libs.versions.targetSdk.get().toInt()
                versionCode = libs.versions.versionCode.get().toInt()
                versionName = libs.versions.versionName.get()
                vectorDrawables.useSupportLibrary = true

                buildTypes {
                    release {
                        signingConfig = signingConfigs.getByName("debug")
                        isMinifyEnabled = true
                        isShrinkResources = true
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }

                    debug {
                        isMinifyEnabled = false
                        isShrinkResources = false
                    }
                }

                packagingOptions.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }
}
