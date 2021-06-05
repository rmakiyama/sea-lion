package com.rmakiyama.sealion.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmakiyama.sealion.domain.Task
import com.rmakiyama.sealion.domain.TaskId
import com.rmakiyama.sealion.ui.theme.SeaLionTheme
import com.rmakiyama.sealion.ui.widget.SeaLionFloatingActionButton
import com.rmakiyama.sealion.ui.widget.SeaLionTopBar
import com.rmakiyama.sealion.ui.widget.TaskListItem

@Composable
fun HomeScreen(
    onClickTask: (taskId: TaskId) -> Unit,
    onClickAddTask: () -> Unit,
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val tasks: List<Task> by viewModel.tasks.collectAsState(initial = emptyList())
    HomeScreen(
        tasks = tasks,
        onClickTask = onClickTask,
        onClickAddTask = onClickAddTask,
    )
}

@Composable
fun HomeScreen(
    tasks: List<Task>,
    onClickTask: (taskId: TaskId) -> Unit,
    onClickAddTask: () -> Unit,
) {
    Scaffold(
        topBar = { SeaLionTopBar { Text(text = "TODO") } },
        floatingActionButton = { SeaLionFloatingActionButton { onClickAddTask() } },
        floatingActionButtonPosition = FabPosition.Center,
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks) { task ->
                TaskListItem(task = task, onClickTask = onClickTask)
            }
        }
    }
}

@Preview(name = "home")
@Composable
fun DefaultPreview() {
    val tasks = listOf(
        Task(title = "task 1"),
        Task(title = "task 2", description = "task description")
    )
    SeaLionTheme {
        HomeScreen(tasks = tasks, onClickTask = {}, onClickAddTask = {})
    }
}
