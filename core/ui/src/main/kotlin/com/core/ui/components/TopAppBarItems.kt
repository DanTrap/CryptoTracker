package com.core.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun IconButtonBackArrow(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(modifier = modifier, onClick = onClick) {
        Icon(
            painter = rememberVectorPainter(Icons.AutoMirrored.Rounded.ArrowBack),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun Preview() {
    IconButtonBackArrow {}
}
