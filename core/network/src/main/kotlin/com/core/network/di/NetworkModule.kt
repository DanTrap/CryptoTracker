package com.core.network.di

import com.core.network.api.service.ApiConstants
import com.core.network.api.service.CoinService
import com.core.network.api.service.CoinServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.serialization.kotlinx.serialization
import kotlinx.serialization.json.Json
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    provideKtorClient()
    singleOf(::CoinServiceImpl) bind CoinService::class
}

internal fun Module.provideKtorClient(): KoinDefinition<HttpClient> = single {
    HttpClient(OkHttp) {
        expectSuccess = true
        install(HttpTimeout) {
            requestTimeoutMillis = 10_000
            connectTimeoutMillis = 5_000
        }
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                }
            )
            serialization(
                contentType = ContentType.Any,
                format = Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                }
            )
        }
        defaultRequest {
            header(ApiConstants.Headers.API_KEY, "CG-HtDegTZ6jrzWPVHBoVMFe8Ko ")
            url(ApiConstants.Endpoints.BASE_URL)
        }
    }
}
