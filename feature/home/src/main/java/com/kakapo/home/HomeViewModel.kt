package com.kakapo.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    val viewState: StateFlow<HomeViewContract.ViewState> get() = _viewState
    private val _viewState = MutableStateFlow(HomeViewContract.ViewState())
}