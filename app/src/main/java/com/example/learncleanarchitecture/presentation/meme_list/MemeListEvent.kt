package com.example.learncleanarchitecture.presentation.meme_list

sealed interface MemeListEvent {
    data object Refresh: MemeListEvent
}