package com.kakapo.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kakapo.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    val viewState: StateFlow<HomeViewContract.ViewState> get() = _viewState
    private val _viewState = MutableStateFlow(HomeViewContract.ViewState())

    fun querySearchChat(query: String){
        _viewState.update { oldValue ->
            oldValue.copy(searchQuery = query)
        }
    }

    fun searchChat(){
        Logger.d("query: ${viewState.value.searchQuery}")
    }
}