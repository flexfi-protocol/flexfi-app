package io.flexfi.app.feature.register.injection

import io.flexfi.app.feature.register.RegisterViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureRegisterModule = module {
    viewModelOf(::RegisterViewModel)
}
