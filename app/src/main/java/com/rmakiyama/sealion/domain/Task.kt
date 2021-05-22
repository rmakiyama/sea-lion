package com.rmakiyama.sealion.domain

import java.util.UUID

class Task private constructor(
    val id: String,
    val title: String,
    val description: String,
    var isCompleted: Boolean,
) {

    constructor(title: String, description: String = "") : this(
        id = UUID.randomUUID().toString(),
        title = title,
        description = description,
        isCompleted = false,
    )

    fun hasDescription(): Boolean = description.isNotEmpty()
}
