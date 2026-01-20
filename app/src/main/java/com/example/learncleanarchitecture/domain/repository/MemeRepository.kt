package com.example.learncleanarchitecture.domain.repository

import com.example.learncleanarchitecture.data.remote.dto.MemeDto
import com.example.learncleanarchitecture.domain.model.Meme
import com.example.learncleanarchitecture.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface MemeRepository {
    suspend fun getMemes(): Flow<Resource<List<Meme>>>
}