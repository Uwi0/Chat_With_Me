package com.kakapo.home

import org.junit.Assert.*
import org.junit.Test

class HomeViewModelTest{

    private val viewModel = HomeViewModel()

    @Test
    fun `when user input search query it has value`(){
        viewModel.querySearchChat(SEARCH_QUERY)
        val uiState = viewModel.uiState.value
        assertEquals(SEARCH_QUERY, uiState.searchQuery)
    }

    @Test
    fun `when user clicked cell chat item give item id`(){
        viewModel.navigateToChatDetail(SELECTED_ID)
        val uiState = viewModel.uiState.value
        assertEquals(SELECTED_ID, uiState.selectedChatId)
    }
    companion object{
        private const val SEARCH_QUERY = "dwi"
        private const val SELECTED_ID = 1
    }
}