package com.example.learncleanarchitecture.data.practice.relations

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: Int,
    val name: String
)
