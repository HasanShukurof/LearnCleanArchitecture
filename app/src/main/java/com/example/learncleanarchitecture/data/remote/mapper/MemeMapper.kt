package com.example.learncleanarchitecture.data.remote.mapper

import com.example.learncleanarchitecture.data.remote.dto.MemeDto
import com.example.learncleanarchitecture.domain.model.Meme

fun MemeDto.toDomain(): Meme {
    return Meme(
        boxCount = boxCount,
        id = id,
        name = name,
        url = url,
        width = width,
        height = height,
        captions = captions
    )
}

fun List<MemeDto>.toDomain(): List<Meme> {
    return map {
        it.toDomain()
    }
}