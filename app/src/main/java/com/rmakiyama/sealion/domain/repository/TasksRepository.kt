package com.rmakiyama.sealion.domain.repository

import com.rmakiyama.sealion.domain.Task
import com.rmakiyama.sealion.domain.TaskId
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    fun loadTasks(): Flow<List<Task>>
    fun findById(taskId: TaskId): Task?
    fun save(task: Task)
    fun updateCompleted(taskId: TaskId, isCompleted: Boolean)
}
