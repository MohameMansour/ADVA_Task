package com.adva.network

data class ErrorModel(
    val errors: List<Error>
)

data class Error(
    val code: String,
    val message: String
)
