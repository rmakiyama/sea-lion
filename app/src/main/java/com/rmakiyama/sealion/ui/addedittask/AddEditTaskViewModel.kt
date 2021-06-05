package com.rmakiyama.sealion.ui.addedittask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmakiyama.sealion.domain.Task
import com.rmakiyama.sealion.domain.TaskId
import com.rmakiyama.sealion.usecase.FindTaskPrams
import com.rmakiyama.sealion.usecase.FindTaskUseCase
import com.rmakiyama.sealion.usecase.SaveTaskPrams
import com.rmakiyama.sealion.usecase.SaveTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
internal class AddEditTaskViewModel @Inject constructor(
    private val findTask: FindTaskUseCase,
    private val saveTask: SaveTaskUseCase,
) : ViewModel() {

    private val task = MutableStateFlow<Task?>(null)
    private val loading = MutableStateFlow<Boolean>(false)

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    val state: Flow<AddEditTaskViewState> = combine(task, loading) { task, loading ->
        AddEditTaskViewState(initialized = true, task = task, loading)
    }

    fun findTask(taskId: TaskId?) {
        viewModelScope.launch {
            runCatching {
                taskId?.let { id -> task.emit(findTask(FindTaskPrams(taskId = id))) }
                    ?: task.emit(null)
            }
        }
    }

    fun handleAction(action: AddEditTaskAction) {
        when (action) {
            is AddEditTaskAction.SaveTask -> {
                saveTask(
                    taskId = action.taskId,
                    title = action.title,
                    description = action.description,
                    isComplete = false
                )
            }
        }
    }

    private fun saveTask(
        taskId: TaskId?,
        title: String,
        description: String,
        isComplete: Boolean,
    ) {
        viewModelScope.launch {
            loading.value = true
            runCatching {
                saveTask(
                    SaveTaskPrams(
                        taskId = taskId,
                        title = title,
                        description = description,
                        isComplete = isComplete,
                    )
                )
                eventChannel.send(Event.TaskSaved)
            }
            loading.value = false
        }
    }

    sealed class Event {
        object TaskSaved : Event()
    }
}
