package com.feature.details.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.feature.details.presentation.details.DetailsViewModel
import com.feature.details.presentation.details.screen.DetailsScreen
import com.feature.details.presentation.details.states.DetailsSideEffect
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Serializable
data class Details(val id: String)

fun NavController.navigateToDetails(navOptions: NavOptions? = null) = navigate(Details, navOptions)

fun NavGraphBuilder.detailsScreen(onNavigateBack: () -> Unit) {
    composable<Details> {
        val details = it.toRoute<Details>()
        DetailsRoute(
            viewModel = koinViewModel(
                parameters = { parametersOf(details.id) }
            ),
            onNavigateBack = onNavigateBack
        )
    }
}

@Composable
internal fun DetailsRoute(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
) {
    val state = viewModel.collectAsState().value

    DetailsScreen(
        modifier = modifier,
        state = state,
        onEvent = viewModel::onEvent
    )

    viewModel.collectSideEffect {
        when (it) {
            DetailsSideEffect.NavigateBack -> onNavigateBack()
        }
    }
}
