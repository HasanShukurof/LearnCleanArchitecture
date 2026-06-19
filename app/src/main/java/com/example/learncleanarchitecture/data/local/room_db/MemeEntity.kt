package com.example.learncleanarchitecture.data.local.room_db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memeTable")
data class MemeEntity(
    @PrimaryKey val id: String?,
    @ColumnInfo("box_count")
    val boxCount: Int?,
    val name: String?,
    val url: String?,
    val width: Int?,
    val height: Int?,
    val captions: Int?,
)
