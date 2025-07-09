package com.example.plantit.features.plant_search.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantit.core.common.Resource
import com.example.plantit.features.plant_search.data.remote.model.toPlant
import com.example.plantit.features.plant_search.domain.use_case.get_plants.GetAllPlantsUseCase
import com.example.plantit.features.plant_search.domain.use_case.search_plants.SearchPlantsUseCase
import kotlinx.coroutines.launch

class PlantSearchViewModel(
    private val getPlantsUseCase: GetAllPlantsUseCase,
    private val searchPlantsUseCase: SearchPlantsUseCase
) : ViewModel() {

    var plantsState by mutableStateOf(PlantListState())
        private set

    init {
        getAllPlants()
    }

    fun onQueryChanged(newQuery: String) {
        plantsState = plantsState.copy(query = newQuery)
    }

    fun getAllPlants() {
        viewModelScope.launch {
            plantsState = plantsState.copy(isLoading = true)

            when (val result = getPlantsUseCase()) {
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
