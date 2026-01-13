package com.example.learncleanarchitecture.data.remote.dto


data class MemeResponseDto(
    val data: List<MemeDataDto>,
    val success: Boolean
)
