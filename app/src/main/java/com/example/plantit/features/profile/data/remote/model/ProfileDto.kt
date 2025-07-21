package com.example.plantit.features.profile.data.remote.model

import com.example.plantit.features.profile.domain.model.Profile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDto(
    @SerialName("user_id")
    val userId: String,
    val username: String? = null,
    @SerialName("is_admin")
    val isAdmin: Boolean = false
)

fun ProfileDto.toProfile(): Profile {
    return Profile(
        userId = userId,
        username = username,
        isAdmin = isAdmin
    )
}
