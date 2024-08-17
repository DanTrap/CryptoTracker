package com.core.network.api.service

import com.core.network.model.CoinDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CoinServiceImpl(private val client: HttpClient) : CoinService {

    override suspend fun getCoinsMarket(currency: String, itemsPerPage: Int): List<CoinDto> {
        return client.get(ApiConstants.Endpoints.COINS_MARKET_PATH) {
            url {
                parameters.append(ApiConstants.Params.CURRENCY, currency)
                parameters.append(ApiConstants.Params.ITEMS_PER_PAGE, itemsPerPage.toString())
            }
        }.body<List<CoinDto>>()
    }
}
