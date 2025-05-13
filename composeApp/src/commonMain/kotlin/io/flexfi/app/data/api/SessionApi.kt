package io.flexfi.app.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.flexfi.app.data.dto.RegisterRequest
import io.flexfi.app.data.dto.RegisterResponse
import io.flexfi.app.libraries.result.Error
import io.flexfi.app.libraries.result.ResultOf
import io.flexfi.app.libraries.result.apiCall

interface SessionApi {
    suspend fun register(request: RegisterRequest): ResultOf<RegisterResponse, Error>

    class Default(private val client: HttpClient) : SessionApi {

        override suspend fun register(request: RegisterRequest): ResultOf<RegisterResponse, Error> =
            apiCall {
                client.post("auth/register") {
                    contentType(ContentType.Application.Json)
                    setBody(request)
                }
            }
    }
}
