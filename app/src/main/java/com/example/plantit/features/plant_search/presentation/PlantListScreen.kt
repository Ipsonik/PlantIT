package com.example.plantit.features.plant_search.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.plantit.R
import com.example.plantit.core.presentation.AddPlantDialog
import com.example.plantit.features.plant_search.domain.model.Plant
import com.example.plantit.core.presentation.PlantItem
import com.example.plantit.core.presentation.SubBoxCircular
import com.example.plantit.core.presentation.SubBoxRounded
import com.example.plantit.features.plant_search.presentation.components.SearchTextField
import com.example.plantit.core.presentation.theme.Dimens
import com.example.plantit.core.presentation.theme.GreenLight
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object PlantList

@Composable
fun PlantSearchScreenRoot(
    plantSearchViewModel: PlantSearchViewModel = koinViewModel(),
    onPlantClick: (Plant) -> Unit,
) {
    val state = plantSearchViewModel.plantsState

    PlantSearchScreen(
        state = state,
        onPlantClick = onPlantClick,
        onQueryChange = {
            plantSearchViewModel.onQueryChanged(it)
            plantSearchViewModel.searchPlants()
        },
        onFieldsChange = plantSearchViewModel::onNewPlantFieldChange,
        onConfirm = plantSearchViewModel::onDialogConfirm,
        onDismiss = plantSearchViewModel::onDialogDismiss,
        onDialogOpen = plantSearchViewModel::onDialogOpen
    )
}

@Composable
fun PlantSearchScreen(
    state: PlantListState,
    onPlantClick: (Plant) -> Unit,
    onQueryChange: (String) -> Unit,
    onFieldsChange: (String, String, String, String, String, String, String, String) -> Unit = { _, _, _, _, _, _, _, _ -> },
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {},
    onDialogOpen: () -> Unit = {}
) {
    if (state.isDialogOpen) {
        AddPlantDialog(
            state = state,
            onFieldsChange = onFieldsChange,
            onConfirm = onConfirm,
            onDismiss = onDismiss
        )
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.bigSpacing, vertical = Dimens.smallPadding)
    ) {
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                SearchTextField(
                    modifier = Modifier.fillMaxWidth(), text = state.query, onValueChange = onQueryChange, isLoading = state.isLoading
                )
            }
        }
        if (state.isAdmin)
            item {
                Spacer(modifier = Modifier.height(Dimens.smallSpacing))
                SubBoxRounded {
                    Row(Modifier.fillMaxWidth().clickable{ onDialogOpen()}, horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        Text("Dodaj roślinę (admin only)", color = Color.White)
                        Spacer(modifier = Modifier.width(Dimens.smallSpacing))
                        IconButton(onClick = {
                            onDialogOpen()
                        }) {
                            Icon(painter = painterResource(R.drawable.ic_add), contentDescription = "Add plant", tint = GreenLight)
                        }
                    }
                }
            }

        items(state.plants.orEmpty()) { plant ->
            if (state.error.isNullOrEmpty())
                PlantItem(plant = plant, onClick = { onPlantClick(plant) })
        }
        item {
            state.error?.let { error ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
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