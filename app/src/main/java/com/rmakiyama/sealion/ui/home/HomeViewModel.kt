package com.rmakiyama.sealion.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmakiyama.sealion.domain.TaskId
import com.rmakiyama.sealion.usecase.LoadTasksUseCase
import com.rmakiyama.sealion.usecase.UpdateTaskCompletedPrams
import com.rmakiyama.sealion.usecase.UpdateTaskCompletedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    loadTasks: LoadTasksUseCase,
    private val updateTaskCompleted: UpdateTaskCompletedUseCase,
) : ViewModel() {
    val tasks = loadTasks(Unit)

    fun updateCompleted(taskId: TaskId, icCompleted: Boolean) {
        viewModelScope.launch {
            runCatching {
                updateTaskCompleted(UpdateTaskCompletedPrams(taskId, icCompleted))
            }
        }
    }
}
