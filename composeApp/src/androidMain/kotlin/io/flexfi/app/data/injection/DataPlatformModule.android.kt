package io.flexfi.app.data.injection

import io.flexfi.app.data.local.datastore.createAndroidDataStore
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val dataPlatformModule = module {
    singleOf(::createAndroidDataStore)
}
