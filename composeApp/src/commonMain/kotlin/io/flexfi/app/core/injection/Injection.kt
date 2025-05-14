package io.flexfi.app.core.injection

import io.flexfi.app.data.injection.dataModule
import io.flexfi.app.data.injection.dataPlatformModule
import io.flexfi.app.feature.register.injection.featureRegisterModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            dataModule,
            dataPlatformModule,
            featureRegisterModule,
        )
    }
}
