package com.example.learncleanarchitecture.data.local_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addNote(note: NoteEntity): Long

    @Query("DELETE FROM note_table WHERE id = :id")
    suspend fun deleteNoteById(id: Long)

    @Query("SELECT * FROM note_table")
    fun getAllNotes(): Flow<List<NoteEntity>>

}