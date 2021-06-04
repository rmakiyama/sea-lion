package com.rmakiyama.sealion.ui.addedittask

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmakiyama.sealion.R
import com.rmakiyama.sealion.domain.Task
import com.rmakiyama.sealion.domain.TaskId
import com.rmakiyama.sealion.ui.theme.SeaLionTheme
import com.rmakiyama.sealion.ui.widget.SeaLionTopBar
import com.rmakiyama.sealion.ui.widget.UndecoratedTextField


@Composable
fun AddEditTaskScreen(
    taskId: TaskId? = null,
    navigateUp: () -> Unit,
) {
    val viewModel: AddEditTaskViewModel = hiltViewModel()
    val task = taskId?.let { id -> viewModel.findTask(taskId = id) }
    AddEditTaskScreen(
        task = task,
        navigateUp = navigateUp,
        onSave = { _, _, _ -> },
    )
}

@Composable
private fun AddEditTaskScreen(
    task: Task? = null,
    navigateUp: () -> Unit,
    onSave: (title: String, description: String, isCompleted: Boolean) -> Unit,
) {
    Scaffold(
        topBar = { SeaLionTopBar(navigateUp = navigateUp) },
    ) {
        var title by remember { mutableStateOf(task?.title.orEmpty()) }
        var description by remember { mutableStateOf(task?.description.orEmpty()) }
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight()
                    .padding(bottom = 72.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                UndecoratedTextField(
                    modifier = Modifier.fillMaxHeight(),
                    value = title,
                    onValueChange = { title = it },
                    style = MaterialTheme.typography.h5,
                    singleLine = true,
                    hint = stringResource(id = R.string.hint_task_title)
                )
                Spacer(modifier = Modifier.size(12.dp))
                UndecoratedTextField(
                    modifier = Modifier.fillMaxHeight(),
                    value = description,
                    onValueChange = { description = it },
                    style = MaterialTheme.typography.body1,
                    hint = stringResource(id = R.string.hint_task_description)
                )
            }
            Button(
                onClick = { onSave(title, description, false) },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Text(text = "save")
            }
        }
    }
}

@Preview(name = "addedittask")
@Composable
private fun AddEditTaskPreview() {
    SeaLionTheme {
        AddEditTaskScreen(task = null, navigateUp = {}, onSave = { _, _, _ -> })
    }
}
