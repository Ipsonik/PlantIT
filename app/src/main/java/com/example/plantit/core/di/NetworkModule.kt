package com.example.plantit.core.di

import com.example.plantit.BuildConfig
import com.example.plantit.core.common.Constants
import com.example.plantit.core.domain.use_case.get_access_token.GetAccessTokenUseCase
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {

    single<String>(named("SupabaseHost")) { BuildConfig.SUPABASE_URL.removePrefix("https://") }
    single<String>(named("SupabaseRestPath")) { Constants.REST_PATH }
    single<String>(named("SupabaseAuthPath")) { Constants.AUTH_PATH }
    single<String>(named("SupabaseKey")) { BuildConfig.SUPABASE_KEY }

    // client for login / signup
    single<HttpClient>(named("authClient")) {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = get<String>(named("SupabaseHost"))
                    encodedPath = get<String>(named("SupabaseAuthPath"))
                }
                header("apikey", BuildConfig.SUPABASE_KEY)
            }
        }
    }
    // client for all other requests
    single<HttpClient>(named("appClient")) {
        val getAccessTokenUseCase = get<GetAccessTokenUseCase>()

        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = get<String>(named("SupabaseHost"))
                    encodedPath = get<String>(named("SupabaseRestPath"))
                }
                header("apikey", BuildConfig.SUPABASE_KEY)
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        getAccessTokenUseCase()?.let { BearerTokens(it, "") }
                    }
                }
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
    }
}
