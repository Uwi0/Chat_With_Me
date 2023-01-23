package com.kakapo.chat_detail

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ChatDetailViewModelTest{

    private val viewModel = ChatDetailViewModel()

    @Test
    fun `when user select chat item id must match with user selected`(){
        viewModel.requestSelectedChat(SELECTED_CHAT_ID)
        val uiState = viewModel.uiState.value
        assertEquals(SELECTED_CHAT_ID, uiState.chatSelectedId)
    }

    @Test
    fun `when user select another chat chat id change`(){
        viewModel.requestSelectedChat(SELECTED_CHAT_ID_2)
        val uiState = viewModel.uiState.value
        assertEquals(SELECTED_CHAT_ID_2, uiState.chatSelectedId)
    }

    @Test
    fun `when user type a message update message in ui state`(){
        viewModel.observeMessage(FIRST_MESSAGE)
        val uiState = viewModel.uiState.value
        assertEquals(FIRST_MESSAGE, uiState.message)
    }

    @Test
    fun `when user change the message value message in ui state changed`() {
        viewModel.observeMessage(SECOND_MESSAGE)
        val uiState = viewModel.uiState.value
        assertEquals(SECOND_MESSAGE, uiState.message)
    }

    @Test
    fun `when user send a message the size of message is increase`() {
        viewModel.observeMessage(FIRST_MESSAGE)
        viewModel.observeMessage(SECOND_MESSAGE)
        val uiState = viewModel.uiState.value
        assertTrue(
            message = "print value ${uiState.channelMessages}",
            block = { uiState.channelMessages.isNotEmpty() })
    }

    companion object {
        private const val SELECTED_CHAT_ID = 1
        private const val SELECTED_CHAT_ID_2 = 2
        private const val FIRST_MESSAGE = "hello"
        private const val SECOND_MESSAGE = "world"
    }
}