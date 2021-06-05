package com.rmakiyama.sealion.ui.addedittask

import com.rmakiyama.sealion.domain.Task

data class AddEditTaskViewState(
    val initialized: Boolean = false,
    val task: Task? = null,
    val loading: Boolean = false,
) {
    companion object {
        val Empty = AddEditTaskViewState()
    }
}
