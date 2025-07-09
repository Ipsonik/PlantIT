package com.example.plantit.features.plant_detail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantit.features.plant_detail.domain.use_case.AddPlantToMyCollectionUseCase
import com.example.plantit.features.plant_detail.domain.use_case.GetPlantDetailByIdUseCase
import com.example.plantit.features.plant_search.data.remote.model.toPlant
import kotlinx.coroutines.launch

class PlantDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val getPlantDetail: GetPlantDetailByIdUseCase,
    private val addToMyPlants: AddPlantToMyCollectionUseCase
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

    }
}