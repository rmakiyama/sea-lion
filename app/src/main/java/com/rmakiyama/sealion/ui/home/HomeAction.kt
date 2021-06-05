package com.rmakiyama.sealion.ui.home

import com.rmakiyama.sealion.domain.TaskId

internal sealed class HomeAction {
    object NavigateAddTask : HomeAction()
    data class NavigateEditTask(val taskId: TaskId) : HomeAction()
    data class UpdateCompleted(
        val taskId: TaskId,
        val isCompleted: Boolean,
    ) : HomeAction()
}
