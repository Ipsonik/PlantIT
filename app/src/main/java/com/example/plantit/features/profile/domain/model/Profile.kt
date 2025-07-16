package com.example.plantit.features.profile.domain.model

data class Profile(
    val userId: String,
    val username: String? = null,
    val isAdmin: Boolean = false
)
