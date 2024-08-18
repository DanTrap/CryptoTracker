package com.core.domain.repository

import com.core.common.network.Resource
import com.core.domain.model.Coin
import com.core.domain.model.CoinDetails
import kotlinx.coroutines.flow.Flow

interface CoinsRepository {

    fun coins(currency: String): Flow<Resource<List<Coin>>>

    fun getCoinDetails(id: String): Flow<Resource<CoinDetails>>
}
