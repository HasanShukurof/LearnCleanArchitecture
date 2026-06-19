package com.example.learncleanarchitecture.data.remote.mapper

import com.example.learncleanarchitecture.data.local.room_db.MemeEntity
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

fun MemeEntity.toMeme(): Meme {
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

fun Meme.toMemeEntity(): MemeEntity {
    return MemeEntity(
        id = id ?: "",
        boxCount = boxCount,
        name = name,
        url = url,
        width = width,
        height = height,
        captions = captions
    )
}