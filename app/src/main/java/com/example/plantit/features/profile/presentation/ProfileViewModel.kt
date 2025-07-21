package com.example.plantit.features.profile.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantit.core.common.Resource
import com.example.plantit.core.domain.use_case.get_saved_user.GetSavedUserUseCase
import com.example.plantit.features.profile.domain.use_case.GetProfileUseCase
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val getSavedUserUseCase: GetSavedUserUseCase
) : ViewModel() {

    var state by mutableStateOf(ProfileState())
        private set

    fun getProfile() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val userId = getSavedUserUseCase()?.id ?: return@launch
            when (val result = getProfileUseCase(userId)) {
                is Resource.Success -> {
                    state = state.copy(profile = result.data, isLoading = false)
                }

                is Resource.Error -> {
                    state = state.copy(error = result.message, isLoading = false)
                }

                is Resource.Loading -> {}
            }
        }
    }
}