package com.rmakiyama.sealion.usecase

import com.rmakiyama.sealion.domain.Task
import com.rmakiyama.sealion.domain.repository.TasksRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class LoadTasksUseCase @Inject constructor(
    private val tasksRepository: TasksRepository,
) : LoadUseCase<Unit, List<Task>>() {
    override fun load(params: Unit): Flow<List<Task>> {
        return tasksRepository.loadTasks()
    }
}
