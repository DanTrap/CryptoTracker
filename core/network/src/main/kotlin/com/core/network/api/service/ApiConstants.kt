package com.core.network.api.service

internal object ApiConstants {
    object Endpoints {
        const val BASE_URL = "https://api.coingecko.com/api/v3/"
        const val COINS_MARKET_PATH = "coins/markets"
    }

    object Params {
        const val CURRENCY = "vs_currency"
        const val ITEMS_PER_PAGE = "per_page"
    }

    object Headers {
        const val API_KEY = "x-cg-demo-api-key"
    }
}
