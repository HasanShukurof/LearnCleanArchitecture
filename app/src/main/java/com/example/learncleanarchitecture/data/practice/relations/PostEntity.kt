package com.example.learncleanarchitecture.data.practice.relations

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class PostEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val userId: Int
)
