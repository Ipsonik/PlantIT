package com.example.plantit.features.plant_detail.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.plantit.core.presentation.theme.BottomArcShape
import com.example.plantit.core.presentation.theme.Dimens
import com.example.plantit.core.presentation.theme.Green700
import com.example.plantit.core.presentation.theme.Green800
import com.example.plantit.features.plant_search.domain.model.Plant
import com.example.plantit.features.plant_search.domain.model.PlantRequirement
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class PlantDetail(val plantId: Int = -1)

@Composable
fun PlantDetailScreenRoot(viewModel: PlantDetailViewModel = koinViewModel(), onBackClick: () -> Unit) {
    val state = viewModel.plantDetailState

    PlantDetailScreen(
        state = state,
        onAddClick = { viewModel.onAddClick() },
        onBackClick = onBackClick
    )
}

@Composable
fun PlantDetailScreen(
    state: PlantDetailState,
    onAddClick: () -> Unit,
    onBackClick: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Box {
                Image(
                    painter = rememberAsyncImagePainter(state.plant?.photo),
                    contentDescription = "Photo of plant ${state.plant?.photo}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(BottomArcShape)
                )
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                        .background(
                            color = Color.White.copy(alpha = 0.7f),
                            shape = CircleShape
                        )
                        .size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
                Button(
                    onClick = onAddClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Green800),
                    shape = RoundedCornerShape(50),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (-16).dp, y = 0.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
                }

            }
            Spacer(modifier = Modifier.height(Dimens.smallSpacing))
            Text(
                text = state.plant?.name.orEmpty(),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Green700,
                ),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PlantDetailScreenPreview() {
    PlantDetailScreen(
        state = PlantDetailState(
            plant = Plant(
                1, "Test", "https://florastore.com/cdn/shop/files/1711701_Atmosphere_01_SQ_MJ_1800x1800.jpg?v=1734605508",
                PlantRequirement(1, 2, "HIGH", 14, 18, "bigos")
            )
        ), onAddClick = {})
}