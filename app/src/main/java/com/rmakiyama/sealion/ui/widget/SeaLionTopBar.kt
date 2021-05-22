package com.rmakiyama.sealion.ui.widget

import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SeaLionTopBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = title,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        elevation = 0.dp,
        modifier = modifier,
    )
}
