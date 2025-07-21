package com.example.plantit.features.plant_search.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantit.core.common.Resource
import com.example.plantit.core.domain.use_case.get_saved_user.GetSavedUserUseCase
import com.example.plantit.features.plant_search.data.remote.model.CreatePlantDto
import com.example.plantit.features.plant_search.data.remote.model.toPlant
import com.example.plantit.features.plant_search.domain.use_case.add_plant.AddPlantUseCase
import com.example.plantit.features.plant_search.domain.use_case.get_plants.GetAllPlantsUseCase
import com.example.plantit.features.plant_search.domain.use_case.search_plants.SearchPlantsUseCase
import com.example.plantit.features.profile.domain.use_case.GetProfileUseCase
import kotlinx.coroutines.launch

class PlantSearchViewModel(
    private val getPlantsUseCase: GetAllPlantsUseCase,
    private val searchPlantsUseCase: SearchPlantsUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val addPlantUseCase: AddPlantUseCase,
    private val getSavedUserUseCase: GetSavedUserUseCase
) : ViewModel() {

    var plantsState by mutableStateOf(PlantListState())
        private set

    init {
        getAllPlants()
    }

    fun onQueryChanged(newQuery: String) {
        plantsState = plantsState.copy(query = newQuery)
    }

    fun onDialogDismiss() {
        plantsState = plantsState.copy(isDialogOpen = false)
    }

    fun onDialogOpen() {
        plantsState = plantsState.copy(isDialogOpen = true)
    }

    fun onNewPlantFieldChange(
        name: String, photo: String,
        watering: String, fertilization: String,
        light: String, tempMin: String,
        tempMax: String, soil: String
    ) {
        plantsState = plantsState.copy(
            newName = name,
            newPhoto = photo,
            newWatering = watering,
            newFertilization = fertilization,
            newLight = light,
            newTempMin = tempMin,
            newTempMax = tempMax,
            newSoil = soil
        )
    }

    fun onDialogConfirm() {
        viewModelScope.launch {
            plantsState = plantsState.copy(isLoading = true)
            // zbuduj DTO – parsuj liczby
            val dto = CreatePlantDto(
                name = plantsState.newName,
                photo = plantsState.newPhoto,
                watering = plantsState.newWatering.toIntOrNull() ?: 0,
                fertilization = plantsState.newFertilization.toIntOrNull() ?: 0,
                light = plantsState.newLight,
                temperatureMin = plantsState.newTempMin.toIntOrNull() ?: 0,
                temperatureMax = plantsState.newTempMax.toIntOrNull() ?: 0,
                soilType = plantsState.newSoil
            )
            when (val res = addPlantUseCase(dto)) {
                is Resource.Success -> {
                    getAllPlants()
                    plantsState = plantsState.copy(isLoading = false, isDialogOpen = false)
                }
                is Resource.Error -> {
                    plantsState = plantsState.copy(isLoading = false, error = res.message, isDialogOpen = false)
                }

                is Resource.Loading -> {}
            }
        }
    }


    fun getAllPlants() {
        viewModelScope.launch {
            val isAdmin = getProfileUseCase(getSavedUserUseCase()?.id ?: "").data?.isAdmin ?: false
            plantsState = plantsState.copy(isLoading = true)

            when (val result = getPlantsUseCase()) {
                is Resource.Success -> {
                    plantsState = plantsState.copy(
                        plants = result.data?.map { it.toPlant() }.orEmpty(),
                        isLoading = false,
                        isAdmin = isAdmin,
                        error = null
                    )
                }

                is Resource.Error -> {
                    plantsState = plantsState.copy(
                        error = result.message,
                        isLoading = false,
                        isAdmin = isAdmin
                    )
                }

                else -> Unit
            }
        }
    }

    fun searchPlants() {
        viewModelScope.launch {
            plantsState = plantsState.copy(isLoading = true)

            when (val result = searchPlantsUseCase(plantsState.query)) {
                is Resource.Success -> {
                    plantsState = plantsState.copy(
                        plants = result.data?.map { it.toPlant() }.orEmpty(),
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    plantsState = plantsState.copy(
                        error = result.message,
                        isLoading = false
                    )
                }

                else -> Unit
            }
        }
    }
}
