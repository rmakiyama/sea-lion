package com.rmakiyama.sealion.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rmakiyama.sealion.domain.Task
import com.rmakiyama.sealion.ui.theme.SeaLionTheme
import com.rmakiyama.sealion.ui.widget.SeaLionFloatingActionButton
import com.rmakiyama.sealion.ui.widget.SeaLionTopBar
import com.rmakiyama.sealion.ui.widget.TaskListItem
import timber.log.Timber

@Composable
fun Home(
    tasks: List<Task> = emptyList(),
) {
    Scaffold(
        topBar = { SeaLionTopBar({ Text(text = "TODO") }) },
        floatingActionButton = { SeaLionFloatingActionButton { Timber.d("click") } },
        floatingActionButtonPosition = FabPosition.Center,
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks) { task ->
                TaskListItem(task = task)
            }
        }
    }
}

@Preview(name = "home")
@Composable
fun DefaultPreview() {
    val tasks = listOf(
        Task("task 1"),
        Task("task 2", "task description")
    )
    SeaLionTheme {
        Home(tasks = tasks)
    }
}
