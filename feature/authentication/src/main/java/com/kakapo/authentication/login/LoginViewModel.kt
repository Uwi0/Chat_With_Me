package com.kakapo.authentication.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    val uiState: StateFlow<LoginContract.UiState> get() = _uiState
    private val _uiState = MutableStateFlow(LoginContract.UiState())

    fun getUsername(query: String){
        _uiState.update { oldValue ->
            oldValue.copy(username = query)
        }
    }

    fun getPassword(query: String){
        _uiState.update { oldValue ->
            oldValue.copy(password = query)
        }
    }

    fun loginApp(){
        usernameIsEmpty()
        passwordIsEmpty()
        checkCanLogin()
    }

    private fun checkCanLogin() {
        _uiState.update { oldValue ->
            oldValue.copy(canLogin = oldValue.allFormValid())
        }
    }

    private fun usernameIsEmpty(){
        if (uiState.value.username.isEmpty()){
            _uiState.update { oldValue ->
                oldValue.copy(isUsernameInCorrect = true)
            }
        }else{
            _uiState.update { oldValue ->
                oldValue.copy(isUsernameInCorrect = false)
            }
        }
    }

    private fun passwordIsEmpty(){
        if (uiState.value.password.isEmpty()){
            _uiState.update { oldValue ->
                oldValue.copy(isPasswordInCorrect = true)
            }
        }else{
            _uiState.update { oldValue ->
                oldValue.copy(isPasswordInCorrect = false)
            }
        }
    }
}