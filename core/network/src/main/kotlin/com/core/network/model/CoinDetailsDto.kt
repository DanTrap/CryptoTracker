package com.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDetailsDto(
    @SerialName("id")
    val id: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("name")
    val name: String,
    @SerialName("categories")
    val categories: List<String>,
    @SerialName("description")
    val description: Description,
    @SerialName("image")
    val image: Image,
)
