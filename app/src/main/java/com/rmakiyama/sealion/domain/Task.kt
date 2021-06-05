package com.rmakiyama.sealion.domain

data class Task constructor(
    val id: TaskId = TaskId.new(),
    val title: String,
    val description: String = "",
    var isCompleted: Boolean = false,
) {
    fun hasDescription(): Boolean = description.isNotEmpty()
}
