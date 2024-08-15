package com.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.feature.home.presentation.home.HomeViewModel
import com.feature.home.presentation.home.states.HomeSideEffect
import com.feature.home.presentation.home.ui.HomeScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Serializable
object Home

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(Home, navOptions)

fun NavGraphBuilder.homeScreen(onNavigateToSettings: () -> Unit) {
    composable<Home> {
        HomeRoute(onNavigateToSettings = onNavigateToSettings)
    }
}

@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    onNavigateToSettings: () -> Unit,
) {
    val state = viewModel.collectAsState().value

    HomeScreen(state = state, modifier = modifier, onEvent = viewModel::onEvent)

    viewModel.collectSideEffect {
        when (it) {
            HomeSideEffect.NavigateToSettings -> onNavigateToSettings()
        }
    }
}
