package com.example.plantit.app

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantit.core.common.utils.uriToBitmap
import com.example.plantit.core.domain.use_case.get_saved_user.GetSavedUserUseCase
import com.example.plantit.core.domain.use_case.logout.LogoutUseCase
import com.example.plantit.features.ai_helper.presentation.BitmapState
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    val getSavedUserUseCase: GetSavedUserUseCase,
    val logoutUseCase: LogoutUseCase
) : ViewModel() {

    var state by mutableStateOf(BitmapState())
        private set

    var userState by mutableStateOf(UserState())

    fun loadCurrentUser() {
        viewModelScope.launch {
            val user = getSavedUserUseCase()
            userState = userState.copy(
                email = user?.email,
                id = user?.id
            )
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
        }
    }

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
}