package com.rmakiyama.sealion.domain

import java.util.UUID

@JvmInline
value class TaskId(val id: String) {
    companion object {
        fun new(): TaskId = TaskId(UUID.randomUUID().toString())
    }
}
