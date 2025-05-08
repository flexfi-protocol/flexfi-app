package org.flexfi.app.core.domain.session.entity

data class Register(
    val user: User,
    val token: String,
)
