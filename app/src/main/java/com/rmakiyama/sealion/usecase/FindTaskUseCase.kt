package com.rmakiyama.sealion.usecase

import com.rmakiyama.sealion.domain.Task
import com.rmakiyama.sealion.domain.TaskId
import com.rmakiyama.sealion.domain.repository.TasksRepository
import javax.inject.Inject

class FindTaskUseCase @Inject constructor(
    private val tasksRepository: TasksRepository,
) : OneShotUseCase<FindTaskPrams, Task?>() {
    override suspend fun execute(params: FindTaskPrams): Task? {
        return tasksRepository.findById(params.taskId)
    }
}

data class FindTaskPrams(val taskId: TaskId)
