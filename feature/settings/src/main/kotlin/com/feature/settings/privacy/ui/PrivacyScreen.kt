package com.feature.settings.privacy.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.domain.repository.ContactUsManager
import com.core.ui.R
import com.core.ui.components.IconButtonBackArrow
import com.core.ui.components.MainTopBar
import com.feature.settings.privacy.composables.buildInfoString
import com.feature.settings.privacy.states.PrivacyEvent
import org.koin.compose.koinInject

@Composable
fun PrivacyScreen(
    modifier: Modifier = Modifier,
    onEvent: (PrivacyEvent) -> Unit,
) {
    val contactUsManager = koinInject<ContactUsManager>()
    val annotatedString = buildInfoString(
        text = "stringResource(R.string.privacy_policy_text)",
        onClick = contactUsManager::contactUs
    )
    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopBar(
                title = stringResource(id = R.string.privacy_policy),
                navigationIcon = {
                    IconButtonBackArrow { onEvent(PrivacyEvent.OnBackArrowClick) }
                }
            )
        },
    ) { padding ->
        Text(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            text = annotatedString
        )
    }
}
