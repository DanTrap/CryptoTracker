package com.core.domain.usecase

import com.core.common.network.Resource
import com.core.domain.model.Coin
import com.core.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow

class GetCoinsMarketUseCase(private val coinsRepository: CoinsRepository) {

    operator fun invoke(currency: String): Flow<Resource<List<Coin>>> = coinsRepository.coins(currency)
}
