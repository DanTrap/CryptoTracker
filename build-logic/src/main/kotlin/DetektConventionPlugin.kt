import com.buildlogic.libs
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

class DetektConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugins.detekt.get().pluginId)

        extensions.configure<DetektExtension> {
            toolVersion = libs.versions.detekt.get()
            source.setFrom(files(rootDir))
            config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
            buildUponDefaultConfig = true
            parallel = true
            autoCorrect = true
        }

        dependencies {
            add("detektPlugins", libs.detekt.formatting)
            add("detektPlugins", libs.kode.detekt.rules.compose)
            add("detektPlugins", libs.twitter.compose.rules.detekt)
        }

        tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
            exclude("**/build/**")
            exclude("config/**")
        }
    }
}
