@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.example.plantit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.plantit.MainViewModel
import com.example.plantit.model.Plant
import com.example.plantit.PlantListConstant
import kotlinx.serialization.Serializable




@Composable
fun PlantList( modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    val plantListConstant = PlantListConstant()
    val samplePlants = plantListConstant.getPlantsList()

    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(samplePlants) { plant ->
                    PlantItem(plant, navigator)
                }
            }
        },
        detailPane = {
            PlantDetailPane(navigator = navigator, viewModel)
        }
    )
}

@Composable
fun PlantItem(plant: Plant, navigator: ThreePaneScaffoldNavigator<Any>, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navigator.navigateTo(
                    pane = ListDetailPaneScaffoldRole.Detail,
                    content = plant
                )
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(plant.photo),
                contentDescription = "Plant photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = plant.name
            )
        }
    }
}

@Composable
fun PlantDetailPane(navigator: ThreePaneScaffoldNavigator<Any>, viewModel: MainViewModel){
    val content = navigator.currentDestination?.content as? Plant
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (content != null) {
            Image(
                painter = rememberAsyncImagePainter(content.photo),
                contentDescription = "Plant photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            )
            Text(text = content.name)
        }

        Row {
            AssistChip(onClick = {
                if (content!=null)
                    viewModel.onAddNewPlantClick(content)
            }, label = {
                Text(text = "Dodaj")
            })
            AssistChip(onClick = {

            }, label = {
                Text(text = "Cofnij")
            })
        }
    }
}

@Serializable
object PlantList