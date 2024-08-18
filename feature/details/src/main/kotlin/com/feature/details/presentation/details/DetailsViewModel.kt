package com.feature.details.presentation.details

import androidx.lifecycle.viewModelScope
import com.core.common.network.Resource
import com.core.domain.usecase.GetCoinDetailsUseCase
import com.core.mvi.BaseViewModel
import com.core.mvi.emitSideEffect
import com.core.mvi.reducer
import com.feature.details.presentation.details.states.DetailsEvent
import com.feature.details.presentation.details.states.DetailsSideEffect
import com.feature.details.presentation.details.states.DetailsState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DetailsViewModel(
    private val id: String,
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
) : BaseViewModel<DetailsState, DetailsSideEffect, DetailsEvent>(initialState = DetailsState()) {

    init {
        fetchCoinDetails(id)
    }

    override fun onEvent(event: DetailsEvent) {
        when (event) {
            DetailsEvent.OnBackClick -> emitSideEffect(DetailsSideEffect.NavigateBack)
            DetailsEvent.OnTryAgainClick -> fetchCoinDetails(id)
        }
    }

    private fun fetchCoinDetails(id: String) = getCoinDetailsUseCase(id).onEach { resource ->
        when (resource) {
            is Resource.Error -> reducer {
                state.copy(isError = true, isLoading = false, responseError = resource.error)
            }

            Resource.Loading -> reducer {
                state.copy(isLoading = true, isError = false)
            }

            is Resource.Success -> reducer {
                state.copy(
                    coinDetails = resource.data,
                    isLoading = false,
                    isError = false,
                    responseError = null
                )
            }

            is Resource.FromCache -> reducer {
                state.copy(coinDetails = resource.data, isLoading = false, isError = false)
            }
        }
    }.launchIn(viewModelScope)
}
