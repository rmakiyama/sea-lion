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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
internal class AddEditTaskViewModel @Inject constructor(
    private val findTask: FindTaskUseCase,
    private val saveTask: SaveTaskUseCase,
) : ViewModel() {

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    fun findTask(taskId: TaskId?): Flow<Task?> {
        return flow {
            taskId?.let { id -> emit(findTask(FindTaskPrams(taskId = id))) } ?: emit(null)
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
        }
    }

    sealed class Event {
        object TaskSaved : Event()
    }
}
