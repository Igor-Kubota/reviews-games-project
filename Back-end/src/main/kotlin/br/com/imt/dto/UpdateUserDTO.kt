package br.com.imt.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserDTO (
    val name: String,
    val password: String,
    val email: String,
    val img: String?
        )