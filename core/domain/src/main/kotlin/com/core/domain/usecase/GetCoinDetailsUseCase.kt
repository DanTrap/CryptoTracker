package com.core.domain.usecase

import com.core.common.network.Resource
import com.core.domain.model.CoinDetails
import com.core.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow

class GetCoinDetailsUseCase(private val coinsRepository: CoinsRepository) {

    operator fun invoke(id: String): Flow<Resource<CoinDetails>> = coinsRepository.getCoinDetails(id)
}
