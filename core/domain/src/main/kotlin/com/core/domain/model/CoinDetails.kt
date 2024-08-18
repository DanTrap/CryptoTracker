package com.core.domain.model

data class CoinDetails(
    val id: String,
    val symbol: String,
    val name: String,
    val categories: List<String>,
    val description: String,
    val image: String,
)
