package com.example.learncleanarchitecture.presentation.meme_list

import com.example.learncleanarchitecture.domain.model.Meme

data class MemeListState(
    val data: List<Meme> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
