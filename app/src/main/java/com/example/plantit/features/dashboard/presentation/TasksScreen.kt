package com.example.plantit.features.dashboard.presentation

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.plantit.R
import com.example.plantit.app.MainViewModel
import com.example.plantit.core.presentation.theme.Dimens
import com.example.plantit.core.presentation.theme.GreenDark
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object TasksScreen

@Composable
fun TasksScreen(viewModel: MainViewModel = koinViewModel()) {
    val userState = viewModel.userState

    LaunchedEffect(Unit) {
        viewModel.loadCurrentUser()
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Witaj ${userState.email}!")

                Icon(
                    painter = painterResource(id = R.drawable.ic_logout),
                    contentDescription = "Logout user ${userState.email}",
                    tint = GreenDark,
                    modifier = Modifier.size(Dimens.iconSize24).clickable {
                        viewModel.logout()
                    })
            }
        }
    }
}
