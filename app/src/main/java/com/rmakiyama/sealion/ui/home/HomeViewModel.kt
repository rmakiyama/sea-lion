package com.rmakiyama.sealion.ui.home

import androidx.lifecycle.ViewModel
import com.rmakiyama.sealion.usecase.LoadTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    loadTasks: LoadTasksUseCase,
) : ViewModel() {
    val tasks = loadTasks(Unit)
}
