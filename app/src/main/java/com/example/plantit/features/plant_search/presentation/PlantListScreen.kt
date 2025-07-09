package com.example.plantit.features.plant_search.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.plantit.features.plant_search.domain.model.Plant
import com.example.plantit.core.presentation.PlantItem
import com.example.plantit.features.plant_search.presentation.components.SearchTextField
import com.example.plantit.core.presentation.theme.Dimens
import kotlinx.serialization.Serializable

@Serializable
object PlantList

@Composable
fun PlantSearchScreenRoot(
    plantSearchViewModel: PlantSearchViewModel,
    onPlantClick: (Plant) -> Unit,
) {
    val state = plantSearchViewModel.plantsState

    PlantSearchScreen(
        state = state,
        onPlantClick = onPlantClick,
        onQueryChange = {
            plantSearchViewModel.onQueryChanged(it)
            plantSearchViewModel.searchPlants()
        }
    )
}

@Composable
fun PlantSearchScreen(state: PlantListState, onPlantClick: (Plant) -> Unit, onQueryChange: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.bigSpacing, vertical = Dimens.smallSpacing), horizontalArrangement = Arrangement.Center
            ) {
                SearchTextField(
                    modifier = Modifier.fillMaxWidth(), text = state.query, onValueChange = onQueryChange, isLoading = state.isLoading
                )
            }
        }
        items(state.plants.orEmpty()) { plant ->
            if (state.error.isNullOrEmpty())
                PlantItem(plant = plant, onClick = { onPlantClick(plant) })
        }
        item {
            state.error?.let { error ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimens.bigSpacing),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Błąd: $error",
                        color = Color.Red,
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PlantSearchScreenPreview() {
    PlantSearchScreen(state = PlantListState(isLoading = true), onPlantClick = {}, onQueryChange = {})
}