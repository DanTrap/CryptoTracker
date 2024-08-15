package com.feature.settings.language.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.ui.R
import com.core.ui.components.IconButtonBackArrow
import com.feature.settings.language.states.LanguageEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LanguageTopBar(onEvent: (LanguageEvent) -> Unit) {
    TopAppBar(
        modifier = Modifier.padding(horizontal = 12.dp),
        title = {
            Text(
                modifier = Modifier.padding(start = 6.dp),
                text = stringResource(R.string.language),
                style = MaterialTheme.typography.headlineSmall
            )
        },
        navigationIcon = {
            IconButtonBackArrow(onClick = { onEvent(LanguageEvent.OnBackArrowClick) })
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )
}

@Preview
@Composable
private fun LanguageTopBarPreview() {
    LanguageTopBar {}
}
