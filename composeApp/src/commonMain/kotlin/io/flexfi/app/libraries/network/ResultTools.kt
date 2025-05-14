package io.flexfi.app.libraries.network

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

suspend inline fun <reified T> apiCall(
    noinline call: suspend () -> HttpResponse
): Result<T> {
    return runCatching {
        val response = call()
        if (!response.status.isSuccess()) {
            throw Exception("HTTP ${response.status.value}: ${response.status.description}")
        }

        response.body<T>()
    }
}
