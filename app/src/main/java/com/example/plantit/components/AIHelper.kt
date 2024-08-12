package com.example.plantit.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.plantit.BuildConfig
import com.example.plantit.MainViewModel
import com.example.plantit.R
import com.example.plantit.UriReader
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.serialization.Serializable


@Composable
fun AIHelper(viewModel: MainViewModel) {
    val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.API_KEY
    )
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
            uri.let {
                if (uri != null) {
                    viewModel.onImageSelected(uri)
                }
            }
        })

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }) {
                    Text(text = "Wybierz zdjęcie")
                }
            }
        }
        item {
            AsyncImage(
                model = selectedImageUri,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
            )
        }
        item {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillParentMaxWidth()
            ) {
                Button(
                    onClick = {
                        viewModel.onSuggestClick(generativeModel)
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Zgaduj")
                }
                Button(
                    onClick = {
                        viewModel.onSuggestWhatsWrongClick(generativeModel)
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Wylecz")
                }
            }

        }
        item {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (viewModel.state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(32.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                } else {
                    Text(text = viewModel.state.response ?: "")
                }
            }

        }
    }
}

@Serializable
object AIHelper

