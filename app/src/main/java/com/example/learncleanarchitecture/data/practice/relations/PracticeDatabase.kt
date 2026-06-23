package com.example.learncleanarchitecture.data.practice.relations

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class, PostEntity::class],
    version = 1
)
abstract class PracticeDatabase: RoomDatabase() {
    abstract fun practiceDao(): RelationDao
}