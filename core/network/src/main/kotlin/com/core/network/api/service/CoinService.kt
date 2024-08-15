package com.core.network.api.service

import com.core.network.model.CoinDto

interface CoinService {

    suspend fun getCoinsMarket(currency: String, itemsPerPage: Int): List<CoinDto>
}
