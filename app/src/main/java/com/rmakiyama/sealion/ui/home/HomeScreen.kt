package com.rmakiyama.sealion.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.statusBarsPadding
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
        onAction = { action ->
            when (action) {
                HomeAction.NavigateAddTask -> onClickAddTask()
                is HomeAction.NavigateEditTask -> onClickTask(action.taskId)
                is HomeAction.UpdateCompleted -> {
                    viewModel.updateCompleted(action.taskId, action.isCompleted)
                }
            }
        },
    )
}

@Composable
private fun HomeScreen(
    tasks: List<Task>,
    onAction: (HomeAction) -> Unit,
) {
    Scaffold(
        topBar = {
            Surface(elevation = AppBarDefaults.TopAppBarElevation) {
                SeaLionTopBar(modifier = Modifier.statusBarsPadding()) {
                    Text(text = "TODO")
                }
            }
        },
        floatingActionButton = {
            SeaLionFloatingActionButton(modifier = Modifier.navigationBarsPadding()) {
                onAction(HomeAction.NavigateAddTask)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) {
        LazyColumn(
            contentPadding = rememberInsetsPaddingValues(
                insets = LocalWindowInsets.current.navigationBars,
                applyTop = false,
                additionalStart = 16.dp,
                additionalTop = 8.dp,
                additionalEnd = 16.dp,
                additionalBottom = 88.dp,
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks) { task ->
                TaskListItem(
                    task = task,
                    onClickTask = { onAction(HomeAction.NavigateEditTask(it)) },
                    onTaskCompletedChange = { id, isCompleted ->
                        onAction(HomeAction.UpdateCompleted(id, isCompleted))
                    }
                )
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
        HomeScreen(tasks = tasks, onAction = {})
    }
}
