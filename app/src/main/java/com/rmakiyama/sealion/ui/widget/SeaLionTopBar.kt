package com.rmakiyama.sealion.ui.widget

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SeaLionTopBar(
    modifier: Modifier = Modifier,
    navigateUp: (() -> Unit)? = null,
    title: @Composable () -> Unit = {},
) {
    TopAppBar(
        title = title,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        elevation = 0.dp,
        modifier = modifier,
        navigationIcon = if (navigateUp == null) null else {
            {
                IconButton(onClick = { navigateUp() }) {
                    Icon(
                        imageVector = Icons.TwoTone.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        },
    )
}
