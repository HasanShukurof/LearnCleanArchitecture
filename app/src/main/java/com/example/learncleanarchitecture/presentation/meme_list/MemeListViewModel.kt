package com.example.learncleanarchitecture.presentation.meme_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learncleanarchitecture.data.practice.relations.PostEntity
import com.example.learncleanarchitecture.data.practice.relations.RelationDao
import com.example.learncleanarchitecture.data.practice.relations.UserEntity
import com.example.learncleanarchitecture.domain.usecase.GetMemeUseCase
import com.example.learncleanarchitecture.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemeListViewModel @Inject constructor(
    private val getMemeUseCase: GetMemeUseCase,
    private val dao: RelationDao
): ViewModel() {

    private val _state = MutableStateFlow(MemeListState())
    val state = _state.asStateFlow()

    init {
        getMemes()
        testRelation()
    }

    fun testRelation() {
        viewModelScope.launch {
            dao.insertUser(UserEntity(1,"Hasan"))
            dao.insertPosts(listOf(
                PostEntity(1,"Post-1",1),
                PostEntity(2,"Post-2",1),
            ))
            val result = dao.getUserWithPosts(1)
            Log.e("Relation Test: ","User: ${result.user.name}, Posts: ${result.posts.map { it.title }}")
        }
    }

    fun onEvent(event: MemeListEvent) {
        when(event) {
            MemeListEvent.Refresh -> getMemes()
        }
    }

    private fun getMemes() {
        viewModelScope.launch {
            getMemeUseCase().collect { result ->
                when(result) {
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(isLoading = true, error = null)
                        }
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(data = result.data ?: emptyList(), isLoading = false, error = null)
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(data = result.data ?: it.data,error = result.message, isLoading = false)
                        }
                    }
                }
            }
        }
    }


}