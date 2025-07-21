package com.example.plantit.core.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.plantit.core.presentation.theme.GreenDark

@Composable
fun LabeledTextField(label : String, value : String, onValueChange : (String) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = label,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(0.4f)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            modifier = Modifier.weight(0.6f),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor  = GreenDark,
                focusedLabelColor = GreenDark,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LabeledTextFieldPreview() {
    LabeledTextField(label = "Nazwa", value = "Nazwa", onValueChange = {})
}