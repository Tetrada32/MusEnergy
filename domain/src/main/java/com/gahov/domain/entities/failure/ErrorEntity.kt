package com.gahov.domain.entities.failure

data class ErrorEntity(
    val status: String = "error",
    val code: String = "",
    val message: String = ""
)