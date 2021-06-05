package com.rmakiyama.sealion.ui.addedittask

import com.rmakiyama.sealion.domain.TaskId

internal sealed class AddEditTaskAction {
    data class SaveTask(
        val taskId: TaskId?,
        val title: String,
        val description: String,
        val isComplete: Boolean
    ) : AddEditTaskAction()
}
