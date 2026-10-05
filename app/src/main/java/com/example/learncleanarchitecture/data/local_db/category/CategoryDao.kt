package com.example.learncleanarchitecture.data.local_db.category

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert
    fun addCategory(category: CategoryEntity): Long

    @Query("DELETE FROM category_table WHERE id = :id")
    suspend fun deleteCategory(id: Long)

    @Query("SELECT * FROM category_table")
    fun getAllCAtegories(): Flow<List<CategoryEntity>>

}