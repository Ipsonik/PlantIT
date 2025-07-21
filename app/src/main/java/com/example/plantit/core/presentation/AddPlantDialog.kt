package com.example.plantit.core.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantit.core.presentation.theme.GreenDark
import com.example.plantit.core.presentation.theme.GreenLight
import com.example.plantit.features.plant_search.presentation.PlantListState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlantDialog(
    state: PlantListState,
    onFieldsChange: (String, String, String, String, String, String, String, String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        containerColor = GreenLight,
        onDismissRequest = onDismiss,
        title = { Text("Dodaj nową roślinę") },
        text = {
            Column {
                LabeledTextField(
                    label = "Nazwa", value = state.newName,
                    onValueChange = {
                        onFieldsChange(
                            it,
                            state.newPhoto,
                            state.newWatering,
                            state.newFertilization,
                            state.newLight,
                            state.newTempMin,
                            state.newTempMax,
                            state.newSoil
                        )
                    })
                LabeledTextField(
                    label = "URL zdjęcia", value = state.newPhoto,
                    onValueChange = {
                        onFieldsChange(
                            state.newName,
                            it,
                            state.newWatering,
                            state.newFertilization,
                            state.newLight,
                            state.newTempMin,
                            state.newTempMax,
                            state.newSoil
                        )
                    })
                // watering & fertilization
                LabeledTextField(
                    "Podlewanie (dni)",
                    state.newWatering,
                    onValueChange = {
                        onFieldsChange(
                            state.newName,
                            state.newPhoto,
                            it,
                            state.newFertilization,
                            state.newLight,
                            state.newTempMin,
                            state.newTempMax,
                            state.newSoil
                        )
                    })
                LabeledTextField(
                    "Nawożenie (dni)",
                    state.newFertilization,
                    onValueChange = {
                        onFieldsChange(
                            state.newName,
                            state.newPhoto,
                            state.newWatering,
                            it,
                            state.newLight,
                            state.newTempMin,
                            state.newTempMax,
                            state.newSoil
                        )
                    })

                LabeledTextField("Światło", state.newLight, onValueChange = {
                    onFieldsChange(
                        state.newName,
                        state.newPhoto,
                        state.newWatering,
                        state.newFertilization,
                        it,
                        state.newTempMin,
                        state.newTempMax,
                        state.newSoil
                    )
                })
                LabeledTextField("Min temp", state.newTempMin, onValueChange = {
                    onFieldsChange(
                        state.newName,
                        state.newPhoto,
                        state.newWatering,
                        state.newFertilization,
                        state.newLight,
                        it,
                        state.newTempMax,
                        state.newSoil
                    )
                })
                LabeledTextField("Max temp", state.newTempMax, onValueChange = {
                    onFieldsChange(
                        state.newName,
                        state.newPhoto,
                        state.newWatering,
                        state.newFertilization,
                        state.newLight,
                        state.newTempMin,
                        it,
                        state.newSoil
                    )
                })

                // soil dropdown
                var expanded by remember { mutableStateOf(false) }
                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
                    OutlinedTextField(
                        value = state.newSoil,
                       modifier =  Modifier.menuAnchor(),
                        onValueChange = {},
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = GreenDark,
                            focusedLabelColor = GreenDark,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                        ),
                        label = { Text("Podłoże") },
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                        }
                    )
                    ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        listOf("ziemista", "torfowa", "uniwersalna").forEach { opt ->
                            DropdownMenuItem(
                                onClick = {
                                    onFieldsChange(
                                        state.newName, state.newPhoto, state.newWatering,
                                        state.newFertilization, state.newLight, state.newTempMin, state.newTempMax, opt
                                    )
                                    expanded = false
                                },
                                text = { Text(opt) }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
           RoundedTextButton(onClick = onConfirm, text = "Dodaj")
        },
        dismissButton = {
            RoundedTextButton(onClick = onDismiss, text = "Anuluj")        }
    )
}

@Preview
@Composable
fun AddPlantDialogPreview() {
    AddPlantDialog(
        state = PlantListState(),
        onFieldsChange = { _, _, _, _, _, _, _, _ -> },
        onConfirm = {},
        onDismiss = {}
    )
}