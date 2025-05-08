package org.flexfi.app.core.domain.session

import org.flexfi.app.core.domain.session.entity.Register
import org.flexfi.app.data.api.SessionApi
import org.flexfi.app.data.dto.RegisterRequest
import org.flexfi.app.data.dto.RegisterResponse
import org.flexfi.app.data.dto.toDomain
import org.flexfi.app.libraries.result.Error
import org.flexfi.app.libraries.result.ResultOf
import org.flexfi.app.libraries.result.mapSuccess

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