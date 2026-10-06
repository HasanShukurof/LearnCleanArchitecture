package com.example.learncleanarchitecture.di

import android.content.Context
import androidx.room.Room
import com.example.learncleanarchitecture.data.local_db.category.CategoryDao
import com.example.learncleanarchitecture.data.local_db.note.NoteDao
import com.example.learncleanarchitecture.data.local_db.note.NoteDatabase
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
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(context, NoteDatabase::class.java, "note_db").build()

    @Provides
    fun provideNoteDao(db: NoteDatabase): NoteDao = db.noteDao()

    @Provides
    fun provideCategoryDao(db: NoteDatabase): CategoryDao = db.categoryDao()
}