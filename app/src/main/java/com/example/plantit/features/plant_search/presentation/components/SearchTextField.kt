package com.example.plantit.features.plant_search.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.plantit.core.presentation.theme.Dimens
import com.example.plantit.core.presentation.theme.GreenDark

@Composable
fun SearchTextField(modifier: Modifier = Modifier, text: String, onValueChange: (String) -> Unit, isLoading: Boolean = false) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = true,
        shape = RoundedCornerShape(Dimens.bigCornerRadius),
        placeholder = {
            Text(text = "Monstera deliciosa", color = Color.LightGray)
        },
        label = {
            Text(text = "Szukaj", color = Color.Gray)
        },
        trailingIcon = {
            if (isLoading)
                CircularProgressIndicator(
                    modifier = Modifier,
                    color = GreenDark
                )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = GreenDark,
            unfocusedBorderColor = Color.Black,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SearchTextFieldPreview() {
    SearchTextField(text = "", onValueChange = {})
}