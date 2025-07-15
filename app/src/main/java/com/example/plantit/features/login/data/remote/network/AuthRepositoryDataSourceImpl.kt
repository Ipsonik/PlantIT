package com.example.plantit.features.login.data.remote.network

import com.example.plantit.BuildConfig
import com.example.plantit.BuildConfig.apiKey
import com.example.plantit.core.common.Constants
import com.example.plantit.core.common.Resource
import com.example.plantit.features.login.data.remote.network.model.AuthRequest
import com.example.plantit.features.login.data.remote.network.model.AuthResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class AuthRemoteDataSourceImpl(
    private val client: HttpClient
) : AuthRemoteDataSource {
    override suspend fun signUp(email: String, password: String): Resource<AuthResponse> {
        return try {
            val response: AuthResponse = client.post("${Constants.AUTH_BASE_URL}signup") {
                headers {
                    append("Content-Type", "application/json")
                    append("apikey", BuildConfig.SUPABASE_KEY)
                }
                setBody(AuthRequest(email, password))
            }.body()
            Resource.Success(response)
        } catch (e: ClientRequestException) {
            val errorBody = e.response.bodyAsText()
            val errorJson = Json.parseToJsonElement(errorBody).jsonObject
            val authError = AuthResponse(
                accessToken = null,
                refreshToken = null,
                user = null,
                code = errorJson["code"]?.jsonPrimitive?.intOrNull,
                error = errorJson["error_code"]?.jsonPrimitive?.contentOrNull,
                errorMessage = errorJson["msg"]?.jsonPrimitive?.contentOrNull
            )
            Resource.Success(authError)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun login(email: String, password: String): Resource<AuthResponse> {
        try {
            val httpResponse: HttpResponse = client.post("${Constants.AUTH_BASE_URL}token?grant_type=password") {
                headers {
                    append("apikey", BuildConfig.SUPABASE_KEY)
                    append("Content-Type", "application/json")
                }
                setBody(AuthRequest(email, password))
            }

            return if (httpResponse.status.isSuccess()) {
                val response: AuthResponse = httpResponse.body()
                Resource.Success(response)
            } else {
                val errorBody = httpResponse.bodyAsText()
                val statusCode = httpResponse.status
                if (statusCode.value in 400..499) {
                    val errorJson = Json.parseToJsonElement(errorBody).jsonObject
                    val authError = AuthResponse(
                        accessToken = null, refreshToken = null, user = null,
                        code = errorJson["code"]?.jsonPrimitive?.intOrNull ?: statusCode.value,
                        error = errorJson["error_code"]?.jsonPrimitive?.contentOrNull ?: "bad_request",
                        errorMessage = errorJson["msg"]?.jsonPrimitive?.contentOrNull ?: "Invalid request"
                    )
                    Resource.Success(authError)
                } else {
                    val authError = AuthResponse(
                        accessToken = null, refreshToken = null, user = null,
                        code = statusCode.value, // Prosty przykład
                        error = "http_error",
                        errorMessage = "HTTP Error: ${statusCode.description}"
                    )
                    Resource.Success(authError)
                }
            }
        } catch (e: Exception) {
            if (e is ClientRequestException) {
                val errorBody = e.response.bodyAsText()
                val errorJson = Json.parseToJsonElement(errorBody).jsonObject
                val authError = AuthResponse( /* ... */ )
                return Resource.Success(authError)
            }
            return Resource.Error(e.message ?: "Unknown error during login")
        }
    }


}