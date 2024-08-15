package com.feature.settings.privacy.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import com.core.common.utils.SettingsConstants

@Composable
internal fun buildInfoString(text: String, onClick: () -> Unit): AnnotatedString =
    buildAnnotatedString {
        append(text)
        val mail = SettingsConstants.mailTo.ifEmpty { return@buildAnnotatedString }
        val firstIndex = text.lastIndexOf(mail)
        val lastIndex =
            if (firstIndex != -1) firstIndex + mail.length else return@buildAnnotatedString
        addStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline,
            ),
            start = firstIndex,
            end = lastIndex
        )
        addLink(
            clickable = LinkAnnotation.Clickable(
                tag = "mail",
                linkInteractionListener = { onClick() }
            ),
            start = firstIndex,
            end = lastIndex
        )
    }
