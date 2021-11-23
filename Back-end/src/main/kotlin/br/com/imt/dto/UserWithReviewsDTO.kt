package br.com.imt.dto

import br.com.imt.models.Review
import kotlinx.serialization.Serializable

@Serializable
data class UserWithReviewsDTO(
    val id: Int,
    val name: String,
    val email: String,
    )
{
    var reviews= listOf<ReviewUserDTO>()
}