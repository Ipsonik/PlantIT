package com.example.plantit.features.dashboard.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.plantit.app.MainViewModel
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
            Text(text = "Witaj ${userState.email}!")
        }
    }
}

/*
@Composable
fun TaskItem(task: Task) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(task.plant.photo),
                contentDescription = "Plant photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(
                    id = if (task.type == TaskType.WATERING) {
                        R.drawable.watering
                    } else {
                        R.drawable.fertizator
                    }
                ),
                contentDescription = "Plant photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = task.plant.name)
                Text(text = "Data: ${task.date}")
                Text(
                    text = if (task.type == TaskType.WATERING) {
                        "Podlewanie"
                    } else {
                        "Nawożenie"
                    }
                )
            }

        }

    }
}
*/
