package com.core.network.api.service

import com.core.network.model.CoinDetailsDto
import com.core.network.model.CoinDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments

class CoinServiceImpl(private val client: HttpClient) : CoinService {

    override suspend fun getCoinsMarket(currency: String, itemsPerPage: Int): List<CoinDto> {
        return client.get(ApiConstants.Endpoints.COINS_MARKET_PATH) {
            url {
                parameters.append(ApiConstants.Params.CURRENCY, currency)
                parameters.append(ApiConstants.Params.ITEMS_PER_PAGE, itemsPerPage.toString())
            }
        }.body<List<CoinDto>>()
    }

    override suspend fun getCoinDetails(id: String): CoinDetailsDto {
        return client.get(ApiConstants.Endpoints.COIN_DETAILS_PATH) {
            url {
                appendPathSegments(id)
                parameters.append("localization", "false")
                parameters.append("tickers", "false")
                parameters.append("market_data", "false")
                parameters.append("community_data", "false")
                parameters.append("developer_data", "false")
            }
        }.body<CoinDetailsDto>()
    }
}
