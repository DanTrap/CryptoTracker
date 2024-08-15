package com.dantrap.cryptotracker.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.dantrap.cryptotracker.navigation.AppNavHost
import com.core.ui.theme.AppTheme

@Composable
fun Application(modifier: Modifier = Modifier) {
    val navHostController = rememberNavController()
    AppTheme {
        Surface {
            AppNavHost(navController = navHostController, modifier = modifier)
        }
    }
}
