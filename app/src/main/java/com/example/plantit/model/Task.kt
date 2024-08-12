package com.example.plantit.model


data class Task(
    val plant: Plant,
    val date: java.time.LocalDate,
    val type: TaskType
)

enum class TaskType {
    WATERING, FERTILIZATION
}
