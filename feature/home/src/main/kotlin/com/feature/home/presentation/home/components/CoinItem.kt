package com.feature.home.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.domain.model.Coin
import com.core.ui.R
import com.core.ui.components.CoilImage
import com.core.ui.theme.AppTheme
import com.feature.home.model.Currency
import com.feature.home.util.formatCurrentPrice
import com.feature.home.util.formatPricePercentage

@Composable
internal fun CoinItem(
    coin: Coin,
    currency: Currency,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CoilImage(
            modifier = Modifier.size(48.dp),
            imageUrl = coin.image,
            contentDescription = coin.name
        )
        CoinDetails(
            modifier = Modifier.weight(1f),
            name = coin.name,
            symbol = coin.symbol
        )
        CoinPriceDetails(
            currentPrice = coin.currentPrice,
            currency = currency,
            priceChangePercentage24h = coin.priceChangePercentage24h
        )
    }
}

@Composable
private fun CoinDetails(
    name: String,
    symbol: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = AppTheme.colors.grey
            )
        )
        Text(
            text = symbol,
            style = MaterialTheme.typography.bodyLarge.copy(color = AppTheme.colors.lightGrey)
        )
    }
}

@Composable
private fun CoinPriceDetails(
    currentPrice: Double,
    currency: Currency,
    priceChangePercentage24h: Double,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = stringResource(
                R.string.price_format,
                currency.symbol,
                currentPrice.formatCurrentPrice()
            ),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.End
        )
        Text(
            text = priceChangePercentage24h.formatPricePercentage(),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W500),
            color = when {
                priceChangePercentage24h == 0.0 -> AppTheme.colors.grey
                priceChangePercentage24h > 0 -> AppTheme.colors.azure
                else -> AppTheme.colors.curry
            }
        )
    }
}

@Preview
@Composable
private fun CoinItemPrev() {
    CoinItem(
        coin = Coin(
            id = "tether",
            name = "Tether",
            symbol = "USDT",
            image = "f",
            currentPrice = 435.0,
            priceChangePercentage24h = -1.345
        ),
        currency = Currency.USD
    ) {
    }
}
