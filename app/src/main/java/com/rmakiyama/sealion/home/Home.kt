package com.rmakiyama.sealion.home

import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rmakiyama.sealion.ui.theme.SeaLionTheme
import com.rmakiyama.sealion.ui.widget.SeaLionFloatingActionButton
import com.rmakiyama.sealion.ui.widget.SeaLionTopBar
import timber.log.Timber

@Composable
fun Home() {
    Scaffold(
        topBar = { SeaLionTopBar({ Text(text = "TODO") }) },
        floatingActionButton = { SeaLionFloatingActionButton { Timber.d("click") } },
        floatingActionButtonPosition = FabPosition.Center,
    ) {}
}

@Preview(name = "home")
@Composable
fun DefaultPreview() {
    SeaLionTheme {
        Home()
    }
}
