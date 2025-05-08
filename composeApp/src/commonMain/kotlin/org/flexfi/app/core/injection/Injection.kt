package org.flexfi.app.core.injection

import org.flexfi.app.data.injection.dataModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.core.context.startKoin

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            dataModule,
        )
    }
}
