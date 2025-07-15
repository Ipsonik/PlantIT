package com.example.plantit.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.plantit.core.presentation.AuthCheckState
import com.example.plantit.core.presentation.AutoLoginViewModel
import com.example.plantit.features.login.presentation.LoginScreenRoot
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppRoot(
    autoLoginViewModel: AutoLoginViewModel = koinViewModel()
) {
    val state = autoLoginViewModel.state

    LaunchedEffect(Unit) {
        autoLoginViewModel.checkIfLoggedIn()
    }

    when (state) {
        is AuthCheckState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is AuthCheckState.LoggedIn -> {
            MainScreen()
        }
        is AuthCheckState.LoggedOut -> {
            LoginScreenRoot(authViewModel = koinViewModel(), onSuccessfulLogin = {
                autoLoginViewModel.checkIfLoggedIn()
            })
        }
    }
}