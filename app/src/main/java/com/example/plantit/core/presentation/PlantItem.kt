package com.example.plantit.core.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import com.example.plantit.features.plant_search.domain.model.Plant
import com.example.plantit.core.presentation.theme.Dimens

@Composable
fun PlantItem(plant: Plant, onClick: () -> Unit = {}) {
    Spacer(modifier = Modifier.padding(Dimens.smallSpacing))
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(Dimens.bigCornerRadius),
        elevation = CardDefaults.cardElevation(Dimens.verySmallSpacing),
        modifier = Modifier.fillMaxWidth().clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = rememberAsyncImagePainter(plant.photo),
                contentDescription = "Photo of plant ${plant.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(Dimens.imageSize64)
                    .clip(RoundedCornerShape(Dimens.mediumCornerRadius))
            )
            Spacer(modifier = Modifier.width(Dimens.smallSpacing))
            Text(
                text = plant.name
            )
        }
    }
}