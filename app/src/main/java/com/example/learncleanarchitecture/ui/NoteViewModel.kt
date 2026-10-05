package com.example.learncleanarchitecture.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val dao: NoteDao
): ViewModel() {

    val allNote: StateFlow<List<NoteEntity>> = dao.getAllNotes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L),emptyList())

    fun insertNote(title: String, content: String){
        viewModelScope.launch {
            val note = NoteEntity(
                title = title,
                content = content,
                createdAt = System.currentTimeMillis()
            )
            dao.addNote(note)
        }
    }

    fun deleteNote(id: Long) {
        viewModelScope.launch {
            dao.deleteNoteById(id)
        }
    }

}