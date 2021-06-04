package com.rmakiyama.sealion.domain.repository

import com.rmakiyama.sealion.domain.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    fun loadTasks(): Flow<List<Task>>
}
