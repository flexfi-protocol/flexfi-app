package io.flexfi.app.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import io.flexfi.app.domain.session.entity.Register
import io.flexfi.app.domain.session.entity.User

@Serializable
data class RegisterResponse(
    @SerialName("data") val data: Data? = null,
) {
    @Serializable
    data class Data(
        @SerialName("user") val user: User? = null,
        @SerialName("token") val token: String? = null,
    )

    @Serializable
    data class User(
        @SerialName("_id") val id: String? = null,
        @SerialName("email") val email: String? = null,
        @SerialName("firstName") val firstName: String? = null,
        @SerialName("lastName") val lastName: String? = null,
        @SerialName("userReferralCode") val userReferralCode: String? = null,
    )
}

internal fun RegisterResponse.toDomain(): Register =
    Register(
        user = User(
            id = data?.user?.id.orEmpty(),
            email = data?.user?.email.orEmpty(),
            firstName = data?.user?.firstName.orEmpty(),
            lastName = data?.user?.lastName.orEmpty(),
            userReferralCode = data?.user?.userReferralCode,
        ),
        token = data?.token.orEmpty(),
    )
