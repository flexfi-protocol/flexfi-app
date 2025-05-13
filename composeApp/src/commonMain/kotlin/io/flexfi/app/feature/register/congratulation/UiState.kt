package io.flexfi.app.feature.register.congratulation

data class UiState(
    val name: String = "",
    val referralCode: String = "",
    val positionInWaitlist: Int? = null,
    val waitlistSize: Int? = null,
)
