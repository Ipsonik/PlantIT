package com.example.plantit.features.user_plant_list.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantit.core.common.Resource
import com.example.plantit.core.domain.use_case.get_saved_user.GetSavedUserUseCase
import com.example.plantit.features.user_plants.domain.use_case.get_user_plants.GetUserPlantsUseCase
import kotlinx.coroutines.launch

class UserPlantListViewModel(
    private val getUserPlants: GetUserPlantsUseCase,
    private val getSavedUser: GetSavedUserUseCase
) : ViewModel() {

    var state by mutableStateOf(UserPlantListState())
        private set

    init {
        loadPlants()
    }

    fun loadPlants() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            val user = getSavedUser()
            if (user == null) {
                state = state.copy(
                    isLoading = false,
                    error = "Musisz być zalogowany"
                )
                return@launch
            }
            when (val res = getUserPlants(user.id)) {
                is Resource.Success -> {
                    state = state.copy(
                        isLoading = false,
                        plants = res.data.orEmpty()
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = res.message
                    )
                }
                is Resource.Loading -> {
                }
            }
        }
    }
}
