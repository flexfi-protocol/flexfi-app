package org.flexfi.app.core.domain.session.entity

data class User(
    val id: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val userReferralCode: String?,
)
