package com.example.plantit.app

import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantit.core.common.utils.uriToBitmap
import com.example.plantit.features.plant_search.domain.model.Plant
import com.example.plantit.features.ai_helper.presentation.BitmapState
import com.example.plantit.features.plant_list.presentation.MyPlantsState
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    var state by mutableStateOf(BitmapState())
        private set

    // plants of user
    var myPlantsState by mutableStateOf(MyPlantsState())
        private set




    fun onImageSelected(uri: Uri, context: Context) {
        viewModelScope.launch {
            state = state.copy(
                selectedImageBitmap = uriToBitmap(uri, context)
            )
        }
    }

    fun onSuggestClick(model: GenerativeModel) {
        if (state.selectedImageBitmap == null) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(isLoading = true)
            val response = model.generateContent(
                content {
                    image(state.selectedImageBitmap ?: return@content)
                    text("Proszę abyś odpowiedział jaka to roślina? Chce znać tylko jej nazwe i ewentualnie odmiane w formacie takim jak np. Alokazja Black Velvet. Prosze nie odpowiadaj pelnym zdaniem, jesli nie jestes pewny odmiany rosliny to podaj tylko jej glowna nazwe")
                }
            )
            state = state.copy(
                response = response.text ?: "Coś poszło nie tak",
                isLoading = false
            )
        }
    }

    fun onSuggestWhatsWrongClick(model: GenerativeModel) {
        if (state.selectedImageBitmap == null) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(isLoading = true)
            val response = model.generateContent(
                content {
                    image(state.selectedImageBitmap ?: return@content)
                    text("Co dolega tej roslinie?")
                }
            )
            state = state.copy(
                response = response.text ?: "Coś poszło nie tak",
                isLoading = false
            )
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onAddNewPlantClick(plant: Plant) {
        val existingPlant = myPlantsState.myPlants.find { it.id == plant.id }
        if (existingPlant == null) {
        } else {
            Log.i("siema", "Roślina o id ${plant.id} już istnieje w liście. Lista wyglada tak: ${myPlantsState.myPlants.toString()}")

        }
    }
}