package com.rmakiyama.sealion.data

import com.rmakiyama.sealion.domain.Task
import com.rmakiyama.sealion.domain.TaskId
import com.rmakiyama.sealion.domain.repository.TasksRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

internal class InMemoryTasksRepository @Inject constructor() : TasksRepository {

    private val tasks = MutableStateFlow<MutableMap<TaskId, Task>>(createDummyTasks())

    override fun loadTasks(): Flow<List<Task>> {
        return tasks.map { it.values.toList() }
    }

    override fun findById(taskId: TaskId): Task? {
        return tasks.value[taskId]
    }

    override fun save(task: Task) {
        tasks.value = tasks.value.toMutableMap().apply { put(task.id, task) }
    }

    // fixme
    private fun createDummyTasks(): MutableMap<TaskId, Task> {
        val task1 = Task(title = "こんにちは")
        val task2 = Task(title = "タスクを入れよう", description = "どんどん完了させよう！")
        return mutableMapOf(
            task1.id to task1,
            task2.id to task2,
        )
    }
}
