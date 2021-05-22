package com.rmakiyama.sealion.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rmakiyama.sealion.domain.Task
import com.rmakiyama.sealion.ui.theme.SeaLionTheme

@Composable
fun TaskListItem(
    modifier: Modifier = Modifier,
    task: Task,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = {},
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Text(text = task.title)
                Spacer(modifier = Modifier.size(4.dp))
                if (task.hasDescription()) Text(text = task.description)
            }
        }
    }
}

@Preview(name = "Task List Item")
@Composable
private fun TaskItemPreview() {
    val task = Task(title = "task sample", description = "description")
    SeaLionTheme {
        TaskListItem(task = task)
    }
}
