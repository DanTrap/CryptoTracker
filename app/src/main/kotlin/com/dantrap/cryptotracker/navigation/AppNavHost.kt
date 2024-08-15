package com.dantrap.cryptotracker.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.feature.home.navigation.Home
import com.feature.home.navigation.homeScreen
import com.feature.settings.navigation.navigateToSettings
import com.feature.settings.navigation.settingsGraph

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
            onNavigateToSettings = navController::navigateToSettings
        )
        settingsGraph(navController)
    }
}
