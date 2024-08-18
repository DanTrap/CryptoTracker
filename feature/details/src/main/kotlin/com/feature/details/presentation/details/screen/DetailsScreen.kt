package com.feature.details.presentation.details.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.core.ui.R
import com.core.ui.components.CoilImage
import com.core.ui.components.ErrorDialog
import com.core.ui.components.IconButtonBackArrow
import com.core.ui.components.MainTopBar
import com.feature.details.presentation.details.states.DetailsEvent
import com.feature.details.presentation.details.states.DetailsState

@Composable
fun DetailsScreen(
    state: DetailsState,
    modifier: Modifier = Modifier,
    onEvent: (DetailsEvent) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopBar(
                title = state.coinDetails?.name ?: "",
                navigationIcon = {
                    IconButtonBackArrow { onEvent(DetailsEvent.OnBackClick) }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                state.isLoading -> CircularProgressIndicator(Modifier.align(Alignment.Center))

                state.isError -> ErrorDialog(Modifier.align(Alignment.Center)) {
                    onEvent(DetailsEvent.OnTryAgainClick)
                }

                else -> DetailsScreenContent(state, Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun DetailsScreenContent(
    state: DetailsState,
    modifier: Modifier = Modifier,
) {
    state.coinDetails?.let { coinDetails ->
        Column(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(Modifier)
            CoilImage(
                modifier = Modifier
                    .size(96.dp)
                    .align(Alignment.CenterHorizontally),
                imageUrl = coinDetails.image,
                contentDescription = coinDetails.name
            )
            TitleBodyItem(
                title = stringResource(R.string.description),
                body = coinDetails.description
            )
            TitleBodyItem(
                title = stringResource(R.string.categories),
                body = coinDetails.categories.joinToString()
            )
            Spacer(Modifier)
        }
    }
}

@Composable
private fun TitleBodyItem(title: String, body: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = body,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
