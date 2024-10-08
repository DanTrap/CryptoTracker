package com.dantrap.cryptotracker.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.feature.details.navigation.Details
import com.feature.details.navigation.detailsScreen
import com.feature.home.navigation.Home
import com.feature.home.navigation.homeScreen

@Composable
internal fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: Any = Home,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        homeScreen(
            onNavigateToDetails = { navController.navigate(Details(it)) }
        )
        detailsScreen(
            onNavigateBack = navController::navigateUp
        )
    }
}
