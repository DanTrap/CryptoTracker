package com.feature.home.presentation.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.core.domain.model.Coin
import com.core.ui.R
import com.core.ui.components.ErrorDialog
import com.core.ui.theme.AppTheme
import com.feature.home.presentation.home.components.ChipsTopBar
import com.feature.home.presentation.home.components.CoinItem
import com.feature.home.presentation.home.states.HomeEvent
import com.feature.home.presentation.home.states.HomeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    state: HomeState,
    snackbarState: SnackbarHostState,
    pullToRefreshState: PullToRefreshState,
    modifier: Modifier = Modifier,
    onEvent: (HomeEvent) -> Unit,
) {
    Scaffold(
        modifier = modifier.nestedScroll(pullToRefreshState.nestedScrollConnection),
        snackbarHost = {
            SnackbarHost(hostState = snackbarState) {
                Snackbar(
                    snackbarData = it,
                    containerColor = AppTheme.colors.curry,
                    contentColor = AppTheme.colors.white
                )
            }
        },
        topBar = {
            ChipsTopBar(
                title = stringResource(R.string.list_of_cryptocurrencies),
                selectedCurrency = state.currency,
                onChipClick = { onEvent(HomeEvent.OnChipClick(it)) }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            HomeScreenContent(
                modifier = Modifier.fillMaxSize(),
                state = state,
                onEvent = onEvent
            )
            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = pullToRefreshState,
                contentColor = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Composable
private fun HomeScreenContent(
    state: HomeState,
    modifier: Modifier = Modifier,
    onEvent: (HomeEvent) -> Unit,
) {
    Box(modifier = modifier) {
        CoinsList(
            modifier = Modifier.fillMaxSize(),
            state = state,
            onCoinClick = { onEvent(HomeEvent.OnCoinClick(it)) }
        )
        when {
            state.isLoading -> CircularProgressIndicator(Modifier.align(Alignment.Center))

            state.isError -> ErrorDialog(Modifier.align(Alignment.Center)) {
                onEvent(HomeEvent.OnRefresh(state.currency))
            }
        }
    }
}

@Composable
private fun CoinsList(
    state: HomeState,
    modifier: Modifier = Modifier,
    onCoinClick: (Coin) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(state.coins, key = { it.id }) { coin ->
            CoinItem(
                coin = coin,
                currency = state.currency,
                modifier = Modifier.fillMaxWidth()
            ) {
                onCoinClick(coin)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    HomeScreen(
        state = HomeState(),
        pullToRefreshState = rememberPullToRefreshState(),
        snackbarState = SnackbarHostState()
    ) {}
}
