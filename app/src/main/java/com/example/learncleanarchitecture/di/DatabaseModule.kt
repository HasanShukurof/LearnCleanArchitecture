package com.example.learncleanarchitecture.di

import android.content.Context
import androidx.room.Room
import com.example.learncleanarchitecture.data.local.room_db.MemeDao
import com.example.learncleanarchitecture.data.local.room_db.MemeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MemeDatabase {
        return Room.databaseBuilder(
            context,
            MemeDatabase::class.java,
            "meme_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesDao(database: MemeDatabase): MemeDao {
        return database.memeDao()
    }

}