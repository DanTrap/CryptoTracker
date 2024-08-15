import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.buildlogic.configureAndroidCompose
import com.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.apply {
            apply(libs.plugins.android.application.get().pluginId)
            apply(libs.plugins.compose.compiler.get().pluginId)
        }
        extensions.getByType<BaseAppModuleExtension>().let(::configureAndroidCompose)
    }
}
