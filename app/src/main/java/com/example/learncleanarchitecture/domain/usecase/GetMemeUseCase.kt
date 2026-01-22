package com.example.learncleanarchitecture.domain.usecase

import com.example.learncleanarchitecture.data.remote.dto.MemeDto
import com.example.learncleanarchitecture.domain.model.Meme
import com.example.learncleanarchitecture.domain.repository.MemeRepository
import com.example.learncleanarchitecture.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMemeUseCase @Inject constructor ( private val repo: MemeRepository) {
    suspend operator fun invoke(): Flow<Resource<List<Meme>>> {
        return repo.getMemes()
    }
}