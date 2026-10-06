package com.example.learncleanarchitecture.data.local_db.note

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.learncleanarchitecture.data.local_db.category.CategoryDao
import com.example.learncleanarchitecture.data.local_db.category.CategoryEntity

@Database(
    entities = [NoteEntity::class, CategoryEntity::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun categoryDao(): CategoryDao
}