package com.core.data.source.remote

import com.core.network.api.service.CoinService
import com.core.network.model.CoinDto

interface CoinsRemoteDataSource {

    suspend fun getCoins(currency: String, itemsPerPage: Int = 30): List<CoinDto>

    class Base(private val service: CoinService) : CoinsRemoteDataSource {

        override suspend fun getCoins(currency: String, itemsPerPage: Int): List<CoinDto> {
            return service.getCoinsMarket(currency, itemsPerPage)
        }
    }
}
