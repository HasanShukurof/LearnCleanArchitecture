package com.example.learncleanarchitecture.data.local.room_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MemeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMemes(memes: List<MemeEntity>)

    @Query("DELETE FROM memeTable")
    suspend fun deleteMemes()

    @Query("SELECT * FROM memeTable")
    fun getAllMemes(): Flow<List<MemeEntity>>
}