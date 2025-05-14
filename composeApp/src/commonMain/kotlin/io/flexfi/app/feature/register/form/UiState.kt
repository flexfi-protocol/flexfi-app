package io.flexfi.app.feature.register.form

data class UiState(
    val email: String = "",
    val emailError: String? = null,
    val firstName: String = "",
    val firstNameError: String? = null,
    val lastName: String = "",
    val lastNameError: String? = null,
    val referralCode: String = "",
    val password: String = "",
    val passwordError: String? = null,
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null,
    val gdprAccepted: Boolean = false,
    val privacyPolicyAccepted: Boolean = false,
    val loading: Boolean = false,
)