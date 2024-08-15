package com.feature.settings.language.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.core.ui.R
import com.core.ui.components.MainButton
import com.feature.settings.model.AppLanguage

@Composable
fun LanguageItem(
    language: AppLanguage,
    isCurrentLanguage: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val containerColor = if (isCurrentLanguage) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
    }

    val contentColor = if (isCurrentLanguage) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f)
    }

    MainButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        containerColor = containerColor,
        contentColor = contentColor
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 12.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                language.toLanguagePair().let { pair ->
                    Text(text = pair.first)
                    Text(
                        text = pair.second,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
            if (isCurrentLanguage) {
                Icon(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    painter = rememberVectorPainter(image = Icons.Rounded.Check),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun AppLanguage.toLanguagePair(): Pair<String, String> = when (this) {
    AppLanguage.ENGLISH -> stringResource(R.string.en)
}.run { this to stringResource(languageRes) }
