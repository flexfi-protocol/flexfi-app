package io.flexfi.app.domain.session.entity

data class Register(
    val user: User,
    val token: String,
)
