package com.core.data.mappers

import com.core.common.network.ResponseError
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.serialization.JsonConvertException

internal fun Throwable.toResponseError(): ResponseError = when (this) {
    is JsonConvertException -> ResponseError.UNEXPECTED
    is ClientRequestException -> ResponseError.CLIENT
    is ServerResponseException -> ResponseError.SERVER
    else -> ResponseError.UNEXPECTED
}
