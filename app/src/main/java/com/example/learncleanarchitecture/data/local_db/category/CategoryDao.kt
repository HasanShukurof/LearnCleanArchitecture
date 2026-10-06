package com.example.learncleanarchitecture.data.local_db.category

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert
    suspend fun addCategory(category: CategoryEntity): Long

    @Query("DELETE FROM category_table WHERE id = :id")
    suspend fun deleteCategoryById(id: Long)

    @Query("SELECT * FROM category_table")
    fun getAllCategories(): Flow<List<CategoryEntity>>

    @Query("UPDATE note_table SET categoryId = :toId WHERE categoryId = :fromId")
    suspend fun moveNotes(fromId: Long, toId: Long)

    @Transaction
    suspend fun deleteCategoryAndMoveNotes(deleteId: Long, targetId: Long) {
        moveNotes(deleteId, targetId)    // fromId = deleteId, toId = targetId
        deleteCategoryById(deleteId)     // id = deleteId
    }

}