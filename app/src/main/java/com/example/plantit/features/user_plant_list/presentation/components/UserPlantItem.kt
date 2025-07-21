package com.example.plantit.features.user_plant_list.presentation.components

import androidx.compose.foundation.Image
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
import com.example.plantit.core.presentation.theme.Dimens
import com.example.plantit.features.user_plants.domain.model.UserPlant

@Composable
fun UserPlantItem(plant: UserPlant, onClick: () -> Unit = {}) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(Dimens.bigCornerRadius),
        elevation = CardDefaults.cardElevation(Dimens.verySmallSpacing),
        modifier = Modifier
            .padding(Dimens.smallSpacing)
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = rememberAsyncImagePainter(plant.photo),
                contentDescription = "Photo of plant ${plant.customName}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(Dimens.imageSize64)
                    .clip(RoundedCornerShape(Dimens.mediumCornerRadius))
            )
            Spacer(modifier = Modifier.width(Dimens.smallSpacing))
            Text(
                text = plant.customName ?: ""
            )
        }
    }
}