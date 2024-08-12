package com.example.plantit.components

import com.example.plantit.model.Task

data class MyTasksState(
    val myTasks: List<Task> = emptyList()
)
