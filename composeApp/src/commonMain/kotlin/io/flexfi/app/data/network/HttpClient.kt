package io.flexfi.app.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(engin: HttpClientEngine): HttpClient =
    HttpClient(engin) {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println("HTTP call: $message")
                }
            }
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        defaultRequest {
            url("https://flexfi-back.onrender.com/api/")
        }
        //install(Auth) {
        //    bearer {
        //        loadTokens {
        //
        //        }
        //    }
        //}
    }