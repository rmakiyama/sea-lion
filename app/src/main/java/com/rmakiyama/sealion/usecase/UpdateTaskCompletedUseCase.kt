package com.rmakiyama.sealion.usecase

import com.rmakiyama.sealion.domain.TaskId
import com.rmakiyama.sealion.domain.repository.TasksRepository
import javax.inject.Inject

class UpdateTaskCompletedUseCase @Inject constructor(
    private val tasksRepository: TasksRepository,
) : OneShotUseCase<UpdateTaskCompletedPrams, Unit>() {
    override suspend fun execute(params: UpdateTaskCompletedPrams) {
        return tasksRepository.updateCompleted(params.taskId, params.icCompleted)
    }
}

data class UpdateTaskCompletedPrams(
    val taskId: TaskId,
    val icCompleted: Boolean,
)
