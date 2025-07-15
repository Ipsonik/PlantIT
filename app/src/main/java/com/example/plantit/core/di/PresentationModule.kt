package com.example.plantit.core.di

import androidx.lifecycle.SavedStateHandle
import com.example.plantit.features.plant_search.presentation.PlantSearchViewModel
import com.example.plantit.app.MainViewModel
import com.example.plantit.core.presentation.AutoLoginViewModel
import com.example.plantit.features.login.presentation.AuthViewModel
import com.example.plantit.features.plant_detail.presentation.PlantDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { PlantSearchViewModel(get(), get()) }
    viewModel { (handle: SavedStateHandle) ->
        PlantDetailViewModel(handle, get(), get())
    }
    viewModel { AuthViewModel(get(), get(), get()) }
    viewModel { AutoLoginViewModel(get(), get(), get()) }
}
