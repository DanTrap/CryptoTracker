package com.core.common.network

sealed interface Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>
    data class FromCache<out T>(val data: T) : Resource<T>
    data class Error(val error: ResponseError) : Resource<Nothing>
    data object Loading : Resource<Nothing>
}
