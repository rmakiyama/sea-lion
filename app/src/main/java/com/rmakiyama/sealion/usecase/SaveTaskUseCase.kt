package com.rmakiyama.sealion.usecase

import com.rmakiyama.sealion.domain.Task
import com.rmakiyama.sealion.domain.TaskId
import com.rmakiyama.sealion.domain.repository.TasksRepository
import javax.inject.Inject

class SaveTaskUseCase @Inject constructor(
    private val tasksRepository: TasksRepository,
) : OneShotUseCase<SaveTaskPrams, Unit>() {
    override suspend fun execute(params: SaveTaskPrams) {
        val task = Task(
            id = params.taskId ?: TaskId.new(),
            title = params.title.trim(),
            description = params.description.trim(),
            isCompleted = params.isComplete,
        )
        return tasksRepository.save(task)
    }
}

data class SaveTaskPrams(
    val taskId: TaskId?,
    val title: String,
    val description: String,
    val isComplete: Boolean,
)
