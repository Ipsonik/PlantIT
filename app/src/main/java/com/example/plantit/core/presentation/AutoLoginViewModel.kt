package com.example.plantit.core.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantit.core.domain.use_case.get_access_token.GetAccessTokenUseCase
import com.example.plantit.core.domain.use_case.get_saved_user.GetSavedUserUseCase
import com.example.plantit.core.domain.use_case.session_valid.IsSessionValidUseCase
import kotlinx.coroutines.launch

class AutoLoginViewModel(
    private val getSavedUserUseCase: GetSavedUserUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val isSessionValidUseCase: IsSessionValidUseCase
) : ViewModel() {

    var state by mutableStateOf<AuthCheckState>(AuthCheckState.Loading)
        private set

    fun checkIfLoggedIn() {
        viewModelScope.launch {
            val isLoggedIn = isSessionValidUseCase()
            val user = getSavedUserUseCase()
            state = if (isLoggedIn && user != null) {
                AuthCheckState.LoggedIn(user)
            } else {
                AuthCheckState.LoggedOut
            }
        }
    }

}