package com.example.plantit.features.user_plant_list.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantit.core.presentation.SubBoxCircular
import com.example.plantit.core.presentation.SubBoxRounded
import com.example.plantit.core.presentation.theme.Dimens
import com.example.plantit.features.profile.presentation.ProfileSectionRoot
import com.example.plantit.features.user_plant_list.presentation.components.UserPlantItem
import com.example.plantit.features.user_plants.domain.model.UserPlant
import kotlinx.datetime.LocalDate
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
        item {
            ProfileSectionRoot()
            Spacer(modifier = Modifier.height(Dimens.bigSpacing))
        }
        if (state.error == null) {
            item {
                SubBoxRounded {
                    Column {
                        Spacer(modifier = Modifier.height(Dimens.mediumSpacing))
                        Text(text = "Twoje roślinki", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = Color.White)
                        Spacer(modifier = Modifier.height(Dimens.bigSpacing))
                        state.plants.forEach { plant ->
                            UserPlantItem(plant)
                        }
                    }
                }
            }
        } else {
            item {
                Text(text = "Błąd: ${state.error}", color = Color.Red)
            }
        }
    }
}


@Preview
@Composable
fun UserPlantListPreview() {
    UserPlantList(
        state = UserPlantListState(
            plants = listOf(
                UserPlant(
                    id = 1, userId = "fskdjhf", customName = "Monsterka",
                    plantId = 1,
                    lastWatered = LocalDate.parse("2023-05-01"),
                    lastFertilized = LocalDate.parse("2023-05-01"),
                    customWateringInterval = 5,
                    customFertilizationInterval = 9,
                    photo = ""
                ),
                UserPlant(
                    id = 1, userId = "fskdjhf", customName = "Ficusik",
                    plantId = 1,
                    lastWatered = LocalDate.parse("2023-05-01"),
                    lastFertilized = LocalDate.parse("2023-05-01"),
                    customWateringInterval = 5,
                    customFertilizationInterval = 9,
                    photo = ""
                )
            )
        )
    )
}


