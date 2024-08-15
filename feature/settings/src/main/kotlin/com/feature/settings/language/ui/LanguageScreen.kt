package com.feature.settings.language.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.core.ui.R
import com.core.ui.components.IconButtonBackArrow
import com.core.ui.components.MainTopBar
import com.core.ui.components.ProgressIndicatorWithBackground
import com.feature.settings.language.states.LanguageEvent
import com.feature.settings.language.states.LanguageState

@Composable
fun LanguageScreen(
    state: LanguageState,
    modifier: Modifier = Modifier,
    onEvent: (LanguageEvent) -> Unit,
) {
    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        onEvent(LanguageEvent.CheckLanguage(state.currentLanguage.code.name.lowercase()))
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopBar(
                title = stringResource(R.string.language),
                navigationIcon = {
                    IconButtonBackArrow {
                        onEvent(LanguageEvent.OnBackArrowClick)
                    }
                }
            )
        }
    ) { paddingValues ->
        if (state.isLoading) {
            ProgressIndicatorWithBackground()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.Top
                ),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(state.appLanguages) { language ->
                    LanguageItem(
                        language = language,
                        isCurrentLanguage = language == state.currentLanguage
                    ) {
                        onEvent(LanguageEvent.OnLanguageClick(language))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun LanguageScreenPreview() {
    LanguageScreen(
        state = LanguageState()
    ) {}
}
