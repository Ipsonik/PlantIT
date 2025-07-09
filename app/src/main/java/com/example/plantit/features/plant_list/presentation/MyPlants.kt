package com.example.plantit.features.plant_list.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.plantit.core.presentation.PlantItem
import com.example.plantit.app.MainViewModel
import kotlinx.serialization.Serializable

@Serializable
object MyPlants

@Composable
fun MyPlants(viewModel: MainViewModel){
    val plants = viewModel.myPlantsState.myPlants

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(plants) { plant ->
            PlantItem(plant)
        }
    }
}


