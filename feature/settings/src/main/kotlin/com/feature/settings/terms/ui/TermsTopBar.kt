package com.feature.settings.terms.ui

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
import com.feature.settings.terms.states.TermsEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TermsTopBar(onEvent: (TermsEvent) -> Unit) {
    TopAppBar(
        modifier = Modifier.padding(horizontal = 12.dp),
        title = {
            Text(
                modifier = Modifier.padding(start = 6.dp),
                text = stringResource(R.string.terms_of_use),
                style = MaterialTheme.typography.headlineSmall
            )
        },
        navigationIcon = {
            IconButtonBackArrow(onClick = { onEvent(TermsEvent.OnBackArrowClick) })
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )
}

@Preview
@Composable
private fun PrivacyTopBarPreview() {
    TermsTopBar {}
}
