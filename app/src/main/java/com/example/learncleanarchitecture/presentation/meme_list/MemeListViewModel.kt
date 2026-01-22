package com.example.learncleanarchitecture.presentation.meme_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val getMemeUseCase: GetMemeUseCase
): ViewModel() {

    private val _state = MutableStateFlow(MemeListState())
    val state = _state.asStateFlow()

    init {
        getMemes()
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
                            it.copy(error = result.message, isLoading = false)
                        }
                    }
                }
            }
        }
    }


}