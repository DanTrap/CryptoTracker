package com.feature.home.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.core.ui.R
import com.feature.home.presentation.home.HomeViewModel
import com.feature.home.presentation.home.screen.HomeScreen
import com.feature.home.presentation.home.states.HomeEvent
import com.feature.home.presentation.home.states.HomeSideEffect
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Serializable
object Home

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(Home, navOptions)

fun NavGraphBuilder.homeScreen(
    onNavigateToSettings: () -> Unit,
    onNavigateToDetails: (id: String) -> Unit,
) {
    composable<Home> {
        HomeRoute(
            onNavigateToSettings = onNavigateToSettings,
            onNavigateToDetails = onNavigateToDetails
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    onNavigateToSettings: () -> Unit,
    onNavigateToDetails: (id: String) -> Unit,
) {
    val state = viewModel.collectAsState().value
    val snackbarState = remember { SnackbarHostState() }
    val errorMessage = stringResource(R.string.loading_error_snackbar_message)
    val scope = rememberCoroutineScope()
    val pullToRefreshState = rememberPullToRefreshState(70.dp)
    if (pullToRefreshState.isRefreshing) viewModel.onEvent(HomeEvent.OnRefresh(state.currency))

    HomeScreen(
        modifier = modifier,
        state = state,
        snackbarState = snackbarState,
        pullToRefreshState = pullToRefreshState,
        onEvent = viewModel::onEvent
    )

    viewModel.collectSideEffect {
        when (it) {
            HomeSideEffect.NavigateToSettings -> onNavigateToSettings()
            is HomeSideEffect.NavigateToDetails -> onNavigateToDetails(it.id)
            HomeSideEffect.RefreshEnd -> pullToRefreshState.endRefresh()
            HomeSideEffect.RefreshError -> scope.launch {
                snackbarState.currentSnackbarData?.dismiss()
                snackbarState.showSnackbar(errorMessage)
            }
        }
    }
}
