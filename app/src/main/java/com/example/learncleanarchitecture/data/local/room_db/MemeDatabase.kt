package com.example.learncleanarchitecture.data.local.room_db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MemeEntity::class],
    version = 1
)
abstract class MemeDatabase: RoomDatabase() {
    abstract fun memeDao(): MemeDao
}