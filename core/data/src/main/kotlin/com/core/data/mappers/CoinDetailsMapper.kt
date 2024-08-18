package com.core.data.mappers

import android.text.Html
import com.core.database.model.CoinDetailsEntity
import com.core.domain.model.CoinDetails
import com.core.network.model.CoinDetailsDto

internal fun CoinDetailsDto.toDomain(): CoinDetails = CoinDetails(
    id = id,
    symbol = symbol,
    name = name,
    categories = categories,
    description = Html.fromHtml(description.en, Html.FROM_HTML_MODE_LEGACY).toString(),
    image = image.large
)

internal fun CoinDetailsEntity.toDomain(): CoinDetails = CoinDetails(
    id = id,
    symbol = symbol,
    name = name,
    categories = categories,
    description = description,
    image = image
)

internal fun CoinDetailsDto.toEntity(): CoinDetailsEntity = CoinDetailsEntity(
    id = id,
    symbol = symbol,
    name = name,
    categories = categories,
    description = Html.fromHtml(description.en, Html.FROM_HTML_MODE_LEGACY).toString(),
    image = image.large
)
