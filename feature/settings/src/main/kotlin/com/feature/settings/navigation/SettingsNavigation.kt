package com.feature.settings.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.feature.settings.language.states.LanguageSideEffect
import com.feature.settings.language.ui.LanguageScreen
import com.feature.settings.menu.SettingsViewModel
import com.feature.settings.menu.states.SettingsSideEffect
import com.feature.settings.menu.ui.SettingsScreen
import com.feature.settings.privacy.PrivacyViewModel
import com.feature.settings.privacy.states.PrivacySideEffect
import com.feature.settings.privacy.ui.PrivacyScreen
import com.feature.settings.terms.TermsViewModel
import com.feature.settings.terms.states.TermsSideEffect
import com.feature.settings.terms.ui.TermsScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Serializable
object SettingsGraph

@Serializable
internal object Settings

@Serializable
internal object Language

@Serializable
internal object Privacy

@Serializable
internal object Terms

fun NavController.navigateToSettings(navOptions: NavOptions? = null) =
    navigate(SettingsGraph, navOptions)

fun NavGraphBuilder.settingsGraph(navController: NavController) {
    navigation<SettingsGraph>(
        startDestination = Settings
    ) {
        composable<Settings> {
            SettingsRoute(
                onNavigateBack = navController::navigateUp,
                onNavigateToLanguages = { navController.navigate(Language) },
                onNavigateToPrivacy = { navController.navigate(Privacy) },
                onNavigateToTerms = { navController.navigate(Terms) }
            )
        }
        composable<Language> {
            LanguageRoute(
                onNavigateBack = navController::navigateUp
            )
        }
        composable<Privacy> {
            PrivacyRoute(
                onNavigateBack = navController::popBackStack
            )
        }
        composable<Terms> {
            TermsRoute(
                onNavigateBack = navController::navigateUp
            )
        }
    }
}

@Composable
internal fun SettingsRoute(
    onNavigateBack: () -> Unit,
    onNavigateToLanguages: () -> Unit,
    onNavigateToPrivacy: () -> Unit,
    onNavigateToTerms: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = koinViewModel(),
) {
    val state = viewModel.collectAsState().value

    SettingsScreen(
        state = state,
        modifier = modifier,
        onEvent = viewModel::onEvent
    )

    viewModel.collectSideEffect {
        when (it) {
            SettingsSideEffect.NavigateBack -> onNavigateBack()
            SettingsSideEffect.NavigateToLanguages -> onNavigateToLanguages()
            SettingsSideEffect.NavigateToPrivacyPolicy -> onNavigateToPrivacy()
            SettingsSideEffect.NavigateToTerms -> onNavigateToTerms()
        }
    }
}

@Composable
internal fun LanguageRoute(
    modifier: Modifier = Modifier,
    viewModel: com.feature.settings.language.LanguageViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
) {
    val state = viewModel.collectAsState().value

    LanguageScreen(
        state = state,
        modifier = modifier,
        onEvent = viewModel::onEvent
    )

    viewModel.collectSideEffect {
        when (it) {
            LanguageSideEffect.NavigateBack -> onNavigateBack()
        }
    }
}

@Composable
internal fun PrivacyRoute(
    modifier: Modifier = Modifier,
    viewModel: PrivacyViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
) {
    PrivacyScreen(
        modifier = modifier,
        onEvent = viewModel::onEvent
    )

    viewModel.collectSideEffect {
        when (it) {
            PrivacySideEffect.NavigateBack -> onNavigateBack()
        }
    }
}

@Composable
internal fun TermsRoute(
    modifier: Modifier = Modifier,
    viewModel: TermsViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
) {
    TermsScreen(
        modifier = modifier,
        onEvent = viewModel::onEvent
    )

    viewModel.collectSideEffect {
        when (it) {
            TermsSideEffect.NavigateBack -> onNavigateBack()
        }
    }
}
