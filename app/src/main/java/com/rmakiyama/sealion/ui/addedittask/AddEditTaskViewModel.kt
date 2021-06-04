package com.rmakiyama.sealion.ui.addedittask

import androidx.lifecycle.ViewModel
import com.rmakiyama.sealion.domain.Task
import com.rmakiyama.sealion.domain.TaskId
import com.rmakiyama.sealion.usecase.FindTaskPrams
import com.rmakiyama.sealion.usecase.FindTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val findTask: FindTaskUseCase,
) : ViewModel() {

    fun findTask(taskId: TaskId): Task? {
        return findTask(FindTaskPrams(taskId = taskId))
    }
}
