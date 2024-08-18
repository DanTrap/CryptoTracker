import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.util.Properties

class ApiKeyProviderConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        val keysProperties = Properties().apply {
            load(project.rootProject.file("apikey.properties").inputStream())
        }

        extensions.configure<LibraryExtension> {
            defaultConfig.buildConfigField(
                "String",
                "API_KEY",
                keysProperties.getProperty("API_KEY")
            )
        }
    }
}
