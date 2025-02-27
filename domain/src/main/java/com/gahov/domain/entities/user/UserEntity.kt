package com.gahov.domain.entities.user

import java.time.LocalDate

data class UserEntity(
    val id: Long? = null,
    val name: String? = null,
    val lastName: String? = null,
    val birthDate: LocalDate? = null,
    val email: String? = null,
    val avatar: String? = null
)