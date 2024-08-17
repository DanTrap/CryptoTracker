package com.feature.home.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.core.ui.theme.AppTheme
import com.feature.home.model.Currency

@Composable
fun ChipsTopBar(
    modifier: Modifier = Modifier,
    title: String,
    selectedCurrency: Currency,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    shadowElevation: Dp = 4.dp,
    actions: @Composable RowScope.() -> Unit = {},
    onChipClick: (Currency) -> Unit,
) {
    Box(
        Modifier
            .clip(GenericShape { size, _ ->
                lineTo(size.width, 0f)
                lineTo(size.width, Float.MAX_VALUE)
                lineTo(0f, Float.MAX_VALUE)
            })
            .shadow(shadowElevation)
    ) {
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .statusBarsPadding()
                .padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TitleAndActionsRow(title = title, actions = actions)
            CurrencyChipsRow(selectedCurrency = selectedCurrency, onChipClick = onChipClick)
        }
    }
}

@Composable
private fun TitleAndActionsRow(
    title: String,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.87f)
            )
        )
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            content = actions
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CurrencyChipsRow(
    selectedCurrency: Currency,
    onChipClick: (Currency) -> Unit,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
    ) {
        Currency.entries.forEach { currency ->
            CurrencyChip(
                text = currency.name,
                isSelected = selectedCurrency == currency,
                onClick = { onChipClick(currency) },
            )
        }
    }
}

@Composable
private fun CurrencyChip(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    FilterChip(
        modifier = modifier,
        selected = isSelected,
        onClick = onClick,
        label = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = text
            )
        },
        shape = MaterialTheme.shapes.extraLarge,
        border = null,
        colors = FilterChipDefaults.filterChipColors(
            labelColor = AppTheme.colors.black.copy(alpha = 0.87f),
            selectedLabelColor = AppTheme.colors.honeyWax,
            containerColor = AppTheme.colors.black.copy(alpha = 0.12f),
            selectedContainerColor = AppTheme.colors.mandarinPeel
        )
    )
}

@Preview
@Composable
private fun ChipsTopBarPrev() {
    ChipsTopBar(
        title = "List of cryptocurrencies",
        selectedCurrency = Currency.USD
    ) {

    }
}