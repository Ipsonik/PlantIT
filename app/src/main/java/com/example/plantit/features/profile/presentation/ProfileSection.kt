package com.example.plantit.features.profile.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.plantit.R
import com.example.plantit.core.presentation.SubBoxCircular
import com.example.plantit.core.presentation.theme.Dimens
import com.example.plantit.core.presentation.theme.GreenLight
import com.example.plantit.features.profile.domain.model.Profile
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileSectionRoot(viewModel: ProfileViewModel = koinViewModel()) {

    val state = viewModel.state

    LaunchedEffect(Unit) {
        viewModel.getProfile()
    }
    ProfileSection(state)
}

@Composable
fun ProfileSection(state: ProfileState) {
    SubBoxCircular {
        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.height(Dimens.mediumSpacing))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_profile_placeholder),
                        contentDescription = "Profile image of ${state.profile?.username}",
                        modifier = Modifier
                            .size(Dimens.iconSize24)
                            .clickable {

                            },
                        tint = GreenLight
                    )
                    Spacer(modifier = Modifier.width(Dimens.smallSpacing))

                    Text(
                        text = state.profile?.username ?: "Username",
                        fontSize = Dimens.fontSize16,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
                if (state.profile?.isAdmin == true) {
                    Icon(
                        painter = painterResource(R.drawable.ic_add),
                        contentDescription = "Add plant to database if user is admin: ${state.profile.isAdmin}",
                        modifier = Modifier
                            .size(Dimens.iconSize24)
                            .clickable {

                            },
                        tint = GreenLight
                    )
                }
            }
            Spacer(modifier = Modifier.height(Dimens.mediumSpacing))
        }
    }
}


@Preview
@Composable
fun ProfileSectionPreview() {
    ProfileSection(
        ProfileState(
            profile = Profile(
                username = "John Doe",
                userId = "sdfkhjdskjf",
                isAdmin = true
            )
        )
    )
}