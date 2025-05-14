package io.flexfi.app.domain.session

import io.flexfi.app.data.api.SessionApi
import io.flexfi.app.data.dto.RegisterRequest
import io.flexfi.app.data.dto.RegisterResponse
import io.flexfi.app.data.dto.toDomain
import io.flexfi.app.data.local.DefaultLocalDataSource
import io.flexfi.app.data.local.UserPreferences
import io.flexfi.app.domain.session.entity.Register
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    val userPreferences: Flow<UserPreferences>

    suspend fun register(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        referralCodeUsed: String?,
    ): Result<Register>

    class Default(
        private val api: SessionApi,
        private val local: DefaultLocalDataSource,
    ) : SessionRepository {
        override val userPreferences: Flow<UserPreferences> = local.userPreferences

        override suspend fun register(
            email: String,
            password: String,
            firstName: String,
            lastName: String,
            referralCodeUsed: String?,
        ): Result<Register> {
            return api.register(
                RegisterRequest(
                    email,
                    password,
                    firstName,
                    lastName,
                    referralCodeUsed,
                )
            ).map(RegisterResponse::toDomain)
                .onSuccess { storeInLocal(it) }
        }

        private suspend fun storeInLocal(register: Register) {
            local.setName(register.user.firstName)
            local.setToken(register.token)
            register.user.userReferralCode?.let { local.setReferralCode(it) }
        }
    }
}
