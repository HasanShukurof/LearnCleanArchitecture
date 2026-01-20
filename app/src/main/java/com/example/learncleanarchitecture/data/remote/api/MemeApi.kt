package com.example.learncleanarchitecture.data.remote.api

import com.example.learncleanarchitecture.data.remote.dto.MemeDataDto
import com.example.learncleanarchitecture.data.remote.dto.MemeResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface MemeApi {
    @GET("get_memes")
    suspend fun getMemes(): Response<MemeResponseDto>
}