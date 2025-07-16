package com.example.plantit.features.user_plant_list.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.plantit.core.presentation.PlantItem
import com.example.plantit.app.MainViewModel
import com.example.plantit.features.user_plant_list.presentation.components.UserPlantItem
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object UserPlantList

@Composable
fun UserPlantListRoot(viewModel: UserPlantListViewModel = koinViewModel()) {
    val state = viewModel.state
    UserPlantList(state)
}

@Composable
fun UserPlantList(state: UserPlantListState) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        if (state.error == null) {
            items(state.plants) { plant ->
                UserPlantItem(plant)
            }
        } else {
            item {
                Text(text = "Błąd: ${state.error}", color = Color.Red)
            }
        }
    }
}


