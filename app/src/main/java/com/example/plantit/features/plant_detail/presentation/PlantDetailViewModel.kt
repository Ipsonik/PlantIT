package com.example.plantit.features.plant_detail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantit.core.common.Resource
import com.example.plantit.core.domain.use_case.get_saved_user.GetSavedUserUseCase
import com.example.plantit.features.plant_detail.domain.use_case.GetPlantDetailByIdUseCase
import com.example.plantit.features.plant_search.data.remote.model.toPlant
import com.example.plantit.features.user_plants.data.remote.model.CreateUserPlantDto
import com.example.plantit.features.user_plants.domain.use_case.add_user_plant.AddUserPlantUseCase
import kotlinx.coroutines.launch

class PlantDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val getPlantDetail: GetPlantDetailByIdUseCase,
    private val addUserPlant: AddUserPlantUseCase,
    private val getSavedUser: GetSavedUserUseCase
) : ViewModel() {

    var plantDetailState by mutableStateOf(PlantDetailState())
        private set

    init {
        val plantId: Int = checkNotNull(savedStateHandle["plantId"])
        loadPlantDetail(plantId)
    }

    fun loadPlantDetail(id: Int) {
        viewModelScope.launch {
            plantDetailState = plantDetailState.copy(isLoading = true)
            val result = getPlantDetail(id)
            plantDetailState = plantDetailState.copy(
                plant = result.data?.toPlant(),
                isLoading = false,
                error = null
            )
        }
    }

    fun onAddClick() {
        val plant = plantDetailState.plant ?: return

        viewModelScope.launch {
            plantDetailState = plantDetailState.copy(isLoading = true, error = null)

            val user = getSavedUser()
            if (user == null) {
                plantDetailState = plantDetailState.copy(
                    isLoading = false,
                    error = "Musisz być zalogowany"
                )
                return@launch
            }

            val dto = CreateUserPlantDto(
                plantId = plant.id,
                userId = user.id,
                customName = plant.name,
                lastWatered = "",
                lastFertilized = "",
                customWateringInterval = -1,
                customFertilizationInterval = -1,
            )

            when (val res = addUserPlant(dto)) {
                is Resource.Success -> {
                    plantDetailState = plantDetailState.copy(
                        isLoading = false,
                        isAdded = true
                    )
                }
                is Resource.Error -> {
                    plantDetailState = plantDetailState.copy(
                        isLoading = false,
                        error = res.message
                    )
                }

                is Resource.Loading -> {}
            }
        }
    }
}