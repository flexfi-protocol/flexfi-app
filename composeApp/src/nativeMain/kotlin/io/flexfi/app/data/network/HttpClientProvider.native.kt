package io.flexfi.app.data.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual val httpClientEngine: HttpClientEngine = Darwin.create()
