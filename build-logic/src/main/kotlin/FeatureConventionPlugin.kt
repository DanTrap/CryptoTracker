import com.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply {
            apply(libs.plugins.convetion.library.get().pluginId)
            apply(libs.plugins.convetion.compose.lib.get().pluginId)
            apply(libs.plugins.kotlin.serizliation.get().pluginId)
        }

        dependencies {
            add("implementation", project(":core:domain"))
            add("implementation", project(":core:mvi"))
            add("implementation", project(":core:ui"))

            add("implementation", libs.coroutines.core)

            add("implementation", libs.compose.navigation)
            add("implementation", libs.bundles.compose)
            add("debugImplementation", libs.compose.ui.tooling)

            add("implementation", libs.koin.android)
            add("implementation", libs.koin.compose)

            add("implementation", libs.orbit.compose)

            add("implementation", libs.kotlinx.serialization.json)
        }
    }
}
