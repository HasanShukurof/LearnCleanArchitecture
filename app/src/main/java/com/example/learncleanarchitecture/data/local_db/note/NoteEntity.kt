package com.example.learncleanarchitecture.data.local_db.note

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.learncleanarchitecture.data.local_db.category.CategoryEntity

@Entity(
    tableName = "note_table",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns =  ["categoryId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("categoryId")]
)


data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val content: String,
    val createdAt: Long,
    val categoryId: Long? = null
)