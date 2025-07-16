package com.example.plantit.features.login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantit.core.common.Resource
import com.example.plantit.features.login.domain.use_case.login.LoginUseCase
import com.example.plantit.core.domain.use_case.save_auth_info.SaveAuthInfoUseCase
import com.example.plantit.features.login.domain.use_case.sign_up.SignUpUseCase
import com.example.plantit.features.login.presentation.utils.AuthErrorMapper
import kotlinx.coroutines.launch

class AuthViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val loginUseCase: LoginUseCase,
    private val saveAuthInfoUseCase: SaveAuthInfoUseCase
) : ViewModel() {

    var state by mutableStateOf(AuthUiState())
        private set

    fun updateEmail(email: String) {
        state = state.copy(email = email)
    }

    fun updatePassword(password: String) {
        state = state.copy(password = password)
    }

    fun togglePasswordVisibility() {
        state = state.copy(isPasswordVisible = !state.isPasswordVisible)
    }

    fun login(onLoginSuccess: () -> Unit) {
        viewModelScope.launch {
            state = state.copy(isLoading = true, authError = null)

            when (val result = loginUseCase(state.email, state.password)) {
                is Resource.Success -> {
                    val auth = result.data
                    if (auth?.accessToken != null) {
                        saveAuthInfoUseCase(auth) // ⏳ suspendujący zapis

                        state = state.copy(
                            user = auth.user,
                            isLoading = false
                        )

                        onLoginSuccess() // ✅ dopiero po zapisie
                    } else {
                        val mapped = AuthErrorMapper.fromCode(auth?.error)
                        state = state.copy(authErrorType = mapped, isLoading = false)
                    }
                }
                is Resource.Error -> state = state.copy(
                    authError = result.message,
                    isLoading = false
                )
                is Resource.Loading -> {}
            }
        }
    }


    fun signUp() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, authError = null)

            when (val result = signUpUseCase(state.email, state.password)) {
                is Resource.Success -> state = state.copy(
                    user = result.data?.user,
                    isLoading = false
                )

                is Resource.Error -> state = state.copy(
                    authError = result.message,
                    isLoading = false
                )

                else -> Unit
            }
        }
    }
}