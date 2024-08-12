package com.example.plantit

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.plantit.components.MyTasksState
import com.example.plantit.model.Plant
import com.example.plantit.model.Task
import com.example.plantit.model.TaskType
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class MainViewModel(private val uriReader: UriReader) : ViewModel() {

    var state by mutableStateOf(MainState())
        private set

    var myPlantsState by mutableStateOf(MyPlantsState())
        private set
    var myTasksState by mutableStateOf(MyTasksState())
        private set

    fun onImageSelected(uri: Uri) {
        viewModelScope.launch {
            state = state.copy(
                selectedImageBitmap = uriReader.uriToBitmap(uri)
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
            Log.i("siema", state.response.toString())
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
            Log.i("siema", state.response.toString())
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onAddNewPlantClick(plant: Plant) {

        val existingPlant = myPlantsState.myPlants.find { it.id == plant.id }
        if (existingPlant == null) {
            myPlantsState = myPlantsState.copy(
                myPlants = myPlantsState.myPlants + plant
            )

            // Ustalanie dat dla zadań
            val wateringDate = when (plant.plantRequirement.watering) {
                "Rzadko" -> LocalDate.now().plusDays(14)
                "Umiarkowanie" -> LocalDate.now().plusDays(7)
                "Regularnie" -> LocalDate.now().plusDays(4)
                else -> LocalDate.now().plusDays(7) // Domyślna wartość
            }

            val fertilizationDate = when (plant.plantRequirement.fertilization) {
                "Co dwa tygodnie" -> LocalDate.now().plusWeeks(2)
                "Raz w miesiącu", "Co miesiąc" -> LocalDate.now().plusMonths(1)
                "Co dwa miesiące" -> LocalDate.now().plusMonths(2)
                else -> LocalDate.now().plusWeeks(2) // Domyślna wartość
            }

            // Dodanie nowych zadań
            val tasks = listOf(
                Task(plant, wateringDate, TaskType.WATERING),
                Task(plant, fertilizationDate, TaskType.FERTILIZATION)
            )
            myTasksState = myTasksState.copy(
                myTasks = myTasksState.myTasks + tasks
            )
        } else {
            Log.i("siema", "Roślina o id ${plant.id} już istnieje w liście. Lista wyglada tak: ${myPlantsState.myPlants.toString()}")

        }
    }
}

class MainViewModelFactory(private val uriReader: UriReader) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(uriReader) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
