package com.rmakiyama.sealion.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rmakiyama.sealion.domain.Task
import com.rmakiyama.sealion.domain.TaskId
import com.rmakiyama.sealion.ui.theme.SeaLionTheme

@Composable
fun TaskListItem(
    modifier: Modifier = Modifier,
    task: Task,
    onClickTask: (taskId: TaskId) -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .clickable { onClickTask(task.id) }
                .padding(16.dp)
        ) {
            var isCompleted by remember { mutableStateOf(task.isCompleted) }
            Checkbox(
                checked = isCompleted,
                onCheckedChange = { isCompleted = it },
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.body1,
                )
                Spacer(modifier = Modifier.size(4.dp))
                if (task.hasDescription()) {
                    CompositionLocalProvider(LocalContentAlpha.provides(ContentAlpha.medium)) {
                        Text(
                            text = task.description,
                            style = MaterialTheme.typography.body2,
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "Task List Item")
@Composable
private fun TaskItemPreview() {
    val task = Task(title = "task sample", description = "description")
    SeaLionTheme {
        TaskListItem(onClickTask = {}, task = task)
    }
}
