package com.example.plantit.core.presentation

import com.example.plantit.features.login.data.remote.network.model.UserResponse

sealed class AuthCheckState {
    object Loading : AuthCheckState()
    data class LoggedIn(val user: UserResponse) : AuthCheckState()
    object LoggedOut : AuthCheckState()
}
