package io.flexfi.app.domain.session

import io.flexfi.app.domain.session.entity.Register
import io.flexfi.app.data.api.SessionApi
import io.flexfi.app.data.dto.RegisterRequest
import io.flexfi.app.data.dto.RegisterResponse
import io.flexfi.app.data.dto.toDomain
import io.flexfi.app.libraries.result.Error
import io.flexfi.app.libraries.result.ResultOf
import io.flexfi.app.libraries.result.mapSuccess

interface SessionRepository {
    suspend fun register(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        referralCodeUsed: String?,
    ): ResultOf<Register, Error>

    class Default(private val api: SessionApi) : SessionRepository {
        override suspend fun register(
            email: String,
            password: String,
            firstName: String,
            lastName: String,
            referralCodeUsed: String?,
        ): ResultOf<Register, Error> {
            return api.register(
                RegisterRequest(
                    email,
                    password,
                    firstName,
                    lastName,
                    referralCodeUsed,
                )
            ).mapSuccess(RegisterResponse::toDomain)
        }
    }
}