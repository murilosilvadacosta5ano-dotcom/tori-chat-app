package com.example.compose.jetchat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.jetchat.ai.AIClient
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ChatMessage(
    val text: String,
    val isUser: Boolean
)

class MainViewModel : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages = _messages.asStateFlow()

    fun sendMessage(text: String) {
        _messages.value = _messages.value + ChatMessage(text, true)

        viewModelScope.launch {
            val response = try {
                AIClient.ask(text)
            } catch (e: Exception) {
                "erro na IA"
            }

            _messages.value = _messages.value + ChatMessage(response, false)
        }
    }
}
