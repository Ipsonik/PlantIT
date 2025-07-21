package com.example.plantit.features.profile.presentation

import com.example.plantit.features.profile.domain.model.Profile

data class ProfileState(
    val isLoading: Boolean = false,
    val profile: Profile? = null,
    val error: String? = null

)
