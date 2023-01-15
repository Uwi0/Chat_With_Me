package com.kakapo.status.navigation

import androidx.lifecycle.ViewModel
import com.kakapo.status.StatusViewContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class StatusViewModel @Inject constructor(): ViewModel() {

    val viewState: StateFlow<StatusViewContract.ViewState> get() = _viewState
    private val _viewState = MutableStateFlow(StatusViewContract.ViewState())

}