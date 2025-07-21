package com.example.plantit.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.plantit.core.presentation.theme.Dimens
import com.example.plantit.core.presentation.theme.GreenDark

@Composable
fun SubBoxCircular(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(shape = RoundedCornerShape(percent = 50), color = GreenDark)
            .clip(RoundedCornerShape(percent = 50))
            .padding(horizontal = Dimens.veryBigPadding, vertical = Dimens.veryBigPadding)
    ) {
        content()
    }
}

@Composable
fun SubBoxRounded(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(shape = RoundedCornerShape(size = Dimens.veryLargeCornerRadius), color = GreenDark)
            .clip(RoundedCornerShape(Dimens.veryLargeCornerRadius))
            .padding(horizontal = Dimens.veryBigPadding, vertical = Dimens.veryBigPadding)
    ) {
        content()
    }
}