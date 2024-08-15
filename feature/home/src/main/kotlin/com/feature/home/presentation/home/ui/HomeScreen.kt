package com.feature.home.presentation.home.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.core.ui.components.IconButtonSettings
import com.core.ui.components.MainTopBar
import com.feature.home.presentation.home.states.HomeEvent
import com.feature.home.presentation.home.states.HomeState

@Composable
internal fun HomeScreen(state: HomeState, modifier: Modifier = Modifier, onEvent: (HomeEvent) -> Unit) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopBar(
                actions = {
                    IconButtonSettings {
                        onEvent(HomeEvent.OnSettingsClick)
                    }
                }
            )
        }
    ) { padding ->
        HomeScreenContent(state = state, modifier = Modifier.padding(padding), onEvent = onEvent)
    }
}

@Composable
private fun HomeScreenContent(
    state: HomeState,
    modifier: Modifier = Modifier,
    onEvent: (HomeEvent) -> Unit,
) {
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    HomeScreen(HomeState()) {}
}
