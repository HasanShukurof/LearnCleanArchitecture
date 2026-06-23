package com.example.learncleanarchitecture.data.practice.relations

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface RelationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostEntity>)

    @Transaction
    @Query("SELECT * FROM user_table WHERE id=:userId")
    fun getAllPosts(userId: Int): UserWithPosts

}