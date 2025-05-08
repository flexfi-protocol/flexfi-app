package org.flexfi.app.data.injection

import io.ktor.client.engine.HttpClientEngine
import org.flexfi.app.data.network.createHttpClient
import org.flexfi.app.data.network.httpClientEngine
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf<HttpClientEngine>(::httpClientEngine)
    singleOf(::createHttpClient)
}
