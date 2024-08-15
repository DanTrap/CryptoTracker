package com.feature.settings.menu.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.ui.R
import com.core.ui.components.IconButtonBackArrow
import com.core.ui.components.MainButton
import com.core.ui.components.MainTopBar
import com.core.ui.components.ProgressIndicatorWithBackground
import com.feature.settings.menu.states.SettingsEvent
import com.feature.settings.menu.states.SettingsState

@Composable
fun SettingsScreen(
    state: SettingsState,
    modifier: Modifier = Modifier,
    onEvent: (SettingsEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            MainTopBar(
                title = stringResource(id = R.string.settings),
                navigationIcon = {
                    IconButtonBackArrow { onEvent(SettingsEvent.OnBackArrowClick) }
                }
            )
        },
    ) { paddingValues ->
        when (state.isLoading) {
            true -> ProgressIndicatorWithBackground()
            false -> SettingsDestinationsColumn(
                modifier = modifier.padding(paddingValues),
                onEvent = onEvent
            )
        }
    }
}

@Composable
private fun SettingsDestinationsColumn(
    modifier: Modifier = Modifier,
    onEvent: (SettingsEvent) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.Top
        ),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(SettingsDestinations.entries.toList()) {
            MainButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onEvent(it.event) }
            ) {
                Text(text = stringResource(it.stringResource))
            }
        }
    }
}

@Preview
@Composable
private fun SettingScreenPreview() {
    SettingsScreen(SettingsState()) {}
}
