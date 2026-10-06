package com.example.learncleanarchitecture.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learncleanarchitecture.data.local_db.category.CategoryDao
import com.example.learncleanarchitecture.data.local_db.category.CategoryEntity
import com.example.learncleanarchitecture.data.local_db.note.NoteDao
import com.example.learncleanarchitecture.data.local_db.note.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteDao: NoteDao,
    private val categoryDao: CategoryDao
) : ViewModel() {

    val notes: StateFlow<List<NoteEntity>> = noteDao.getAllNotes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())

    val categories: StateFlow<List<CategoryEntity>> = categoryDao.getAllCategories()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())

    fun insertNote(title: String, content: String, categoryId: Long?) {
        if (title.isBlank() && content.isBlank()) return
        viewModelScope.launch {
            noteDao.addNote(
                NoteEntity(
                    title = title.trim(),
                    content = content.trim(),
                    createdAt = System.currentTimeMillis(),
                    categoryId = categoryId
                )
            )
        }
    }

    fun deleteNote(id: Long) {
        viewModelScope.launch { noteDao.deleteNoteById(id) }
    }

    fun addCategory(name: String) {
        if (name.isBlank()) return
        viewModelScope.launch { categoryDao.addCategory(CategoryEntity(name = name.trim())) }
    }

    // toId == null → "kateqoriyasız qalsın" (SET_NULL işə düşür)
    fun deleteCategory(fromId: Long, toId: Long?) {
        viewModelScope.launch {
            if (toId == null) categoryDao.deleteCategoryById(fromId)
            else categoryDao.deleteCategoryAndMoveNotes(fromId, toId)
        }
    }
}