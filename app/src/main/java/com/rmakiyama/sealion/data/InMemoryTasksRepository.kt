package com.rmakiyama.sealion.data

import com.rmakiyama.sealion.domain.Task
import com.rmakiyama.sealion.domain.TaskId
import com.rmakiyama.sealion.domain.repository.TasksRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class InMemoryTasksRepository @Inject constructor() : TasksRepository {

    private val tasks = MutableStateFlow<List<Task>>(createDummyTasks())

    override fun loadTasks(): Flow<List<Task>> {
        return tasks
    }

    override fun findById(taskId: TaskId): Task? {
        return tasks.value.find { it.id == taskId }
    }

    // fixme
    private fun createDummyTasks() = listOf(
        Task(title = "こんにちは"),
        Task(title = "タスクを入れよう", description = "どんどん完了させよう！"),
    )
}
