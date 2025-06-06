package io.flexfi.app.data.injection

import io.flexfi.app.data.api.SessionApi
import io.flexfi.app.data.local.DefaultLocalDataSource
import io.flexfi.app.data.network.createHttpClient
import io.flexfi.app.data.network.httpClientEngine
import io.flexfi.app.domain.session.SessionRepository
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf<HttpClientEngine>(::httpClientEngine)
    singleOf(::createHttpClient)

    singleOf(SessionApi::Default) bind SessionApi::class
    singleOf(SessionRepository::Default) bind SessionRepository::class
    singleOf(::DefaultLocalDataSource)
}
