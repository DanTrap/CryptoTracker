package com.feature.details.presentation.details.states

import androidx.compose.runtime.Immutable
import com.core.common.network.ResponseError
import com.core.domain.model.CoinDetails

@Immutable
data class DetailsState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val responseError: ResponseError? = null,
    val coinDetails: CoinDetails? = null,
)

sealed interface DetailsEvent {
    data object OnBackClick : DetailsEvent
    data object OnTryAgainClick : DetailsEvent
}

sealed interface DetailsSideEffect {
    data object NavigateBack : DetailsSideEffect
}
