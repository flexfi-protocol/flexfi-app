package io.flexfi.app.data.local

data class UserPreferences(
    val name: String,
    val positionInWaitlist: Int?,
    val token: String,
    val referralCode: String,
)
