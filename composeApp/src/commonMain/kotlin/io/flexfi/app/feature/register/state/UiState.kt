package io.flexfi.app.feature.register.state

data class UiState(
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val referralCode: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val gdprAccepted: Boolean = false,
    val privacyPolicyAccepted: Boolean = false,
)
