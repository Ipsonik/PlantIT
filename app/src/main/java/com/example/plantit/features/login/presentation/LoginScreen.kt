package com.example.plantit.features.login.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantit.R
import com.example.plantit.core.presentation.theme.Dimens
import com.example.plantit.core.presentation.theme.Green300
import com.example.plantit.core.presentation.theme.Green400
import com.example.plantit.core.presentation.theme.GreenDark
import com.example.plantit.features.login.presentation.utils.AuthErrorType
import kotlinx.serialization.Serializable

@Serializable
data object LoginScreen

@Composable
fun LoginScreenRoot(
    authViewModel: AuthViewModel,
    onSuccessfulLogin: () -> Unit = {}
) {
    val state = authViewModel.state

    LaunchedEffect(state.user) {
        if (state.user != null) {
            onSuccessfulLogin()
        }
    }

    LoginScreen(
        state = state,
        onEmailChange = { authViewModel.updateEmail(it) },
        onPasswordChange = { authViewModel.updatePassword(it) },
        onPasswordVisibilityToggle = { authViewModel.togglePasswordVisibility() },
        onLoginClick = { authViewModel.login() },
        onSignUpClick = { authViewModel.signUp() }
    )
}

@Composable
fun LoginScreen(
    state: AuthUiState,
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onPasswordVisibilityToggle: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.bigSpacing),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Logowanie", style = MaterialTheme.typography.headlineLarge, color = GreenDark, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            label = { Text("Hasło") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = onPasswordVisibilityToggle) {
                    Icon(
                        imageVector = if (state.isPasswordVisible) Icons.Default.Lock else Icons.Default.LocationOn, //todo change icon
                        contentDescription = null,
                        tint = Green400
                    )
                }
            }
        )

        state.authErrorType?.let { errorType ->
            val message = when (errorType) {
                AuthErrorType.InvalidEmail -> stringResource(R.string.auth_error_invalid_email)
                AuthErrorType.EmailNotConfirmed -> stringResource(R.string.auth_error_email_not_confirmed)
                AuthErrorType.Unknown -> stringResource(R.string.auth_error_unknown)
                AuthErrorType.ValidationFailed -> stringResource(R.string.auth_error_validation_failed)
                AuthErrorType.InvalidCredentials -> stringResource(R.string.auth_error_bad_credentials)
            }

            Spacer(modifier = Modifier.height(Dimens.mediumSpacing))
            Text(
                text = message,
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
        }


        Spacer(modifier = Modifier.height(Dimens.bigSpacing))

        Button(
            onClick = onLoginClick,
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonColors(
                containerColor = Green400,
                contentColor = Color.White,
                disabledContentColor = Green300,
                disabledContainerColor = Color.White
            )
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
            } else {
                Text("Zaloguj się")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onSignUpClick) {
            Text("Nie masz konta? Zarejestruj się", color = GreenDark)
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(state = AuthUiState())
}

