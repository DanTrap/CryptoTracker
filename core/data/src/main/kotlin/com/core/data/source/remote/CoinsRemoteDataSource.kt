package com.core.data.source.remote

import com.core.network.api.service.CoinService
import com.core.network.model.CoinDetailsDto
import com.core.network.model.CoinDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface CoinsRemoteDataSource {

    suspend fun getCoins(currency: String, itemsPerPage: Int = 30): List<CoinDto>

    suspend fun getCoinDetails(id: String): CoinDetailsDto

    class Base(
        private val dispatcher: CoroutineDispatcher,
        private val service: CoinService,
    ) : CoinsRemoteDataSource {

        override suspend fun getCoins(
            currency: String,
            itemsPerPage: Int,
        ): List<CoinDto> = withContext(dispatcher) {
            service.getCoinsMarket(currency, itemsPerPage)
        }

        override suspend fun getCoinDetails(
            id: String,
        ): CoinDetailsDto = withContext(dispatcher) {
            service.getCoinDetails(id)
        }
    }
}
