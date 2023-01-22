package com.kakapo.chat_detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel @Inject constructor(): ViewModel() {

    val uiState: StateFlow<ChatDetailUiState> get() = _uiState
    private val _uiState = MutableStateFlow(ChatDetailUiState())

    fun requestSelectedChat(chatId: Int){
        _uiState.update { oldValue ->
            oldValue.copy(chatSelectedId = chatId)
        }
    }

    fun observeMessage(message: String){
        _uiState.update { oldValue ->
            oldValue.copy(message = message)
        }
    }
}