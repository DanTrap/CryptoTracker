package com.core.data.mappers

import com.core.database.model.CoinEntity
import com.core.domain.model.Coin
import com.core.network.model.CoinDto

internal fun CoinDto.toDomain(): Coin = Coin(
    id = id,
    name = name,
    symbol = symbol.uppercase(),
    image = image,
    currentPrice = currentPrice,
    priceChangePercentage24h = priceChangePercentage24h
)

internal fun CoinEntity.toDomain(): Coin = Coin(
    id = id,
    name = name,
    symbol = symbol,
    image = image,
    currentPrice = currentPrice,
    priceChangePercentage24h = priceChangePercentage24h
)

internal fun CoinDto.toEntity(currency: String): CoinEntity = CoinEntity(
    id = id,
    name = name,
    symbol = symbol.uppercase(),
    image = image,
    currentPrice = currentPrice,
    priceChangePercentage24h = priceChangePercentage24h,
    currency = currency
)
