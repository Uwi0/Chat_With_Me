package com.kakapo.authentication.register

import androidx.lifecycle.ViewModel
import com.kakapo.authentication.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    val uiState: StateFlow<RegisterUIState> get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow(RegisterUIState())

    fun getName(query: String) {
        _uiState.update { oldValue ->
            oldValue.copy(name = query)
        }
    }

    fun getUsername(query: String) {
        _uiState.update { oldValue ->
            oldValue.copy(username = query)
        }
    }

    fun getEmail(query: String){
        _uiState.update { oldValue ->
            oldValue.copy(email = query)
        }
    }

    fun getPassword(query: String){
        _uiState.update { oldValue ->
            oldValue.copy(password = query)
        }
    }

    fun getConfirmedPassword(query: String){
        _uiState.update { oldValue ->
            oldValue.copy(confirmedPassword = query)
        }
    }

    fun validateRegisterFrom(){
        checkName()
        checkUsername()
        checkEmail()
        checkPassword()
        checkConfirmedPassword()
        checkPasswordLength()
        checkPasswordAndConfirmedPasswordAreSame()
    }

    private fun checkName(){
        _uiState.update { oldValue ->
            oldValue.copy(
                nameCorrect = oldValue.nameIsNotEmpty(),
                canRegister = oldValue.nameIsNotEmpty(),
                nameErrorMessage = R.string.name_cant_be_empty
            )
        }
    }

    private fun checkUsername(){
        _uiState.update { oldValue ->
            oldValue.copy(
                usernameCorrect = oldValue.usernameIsNotEmpty(),
                canRegister = oldValue.usernameIsNotEmpty(),
                usernameErrorMessage = R.string.username_cant_be_empty
            )
        }
    }

    private fun checkEmail(){
        _uiState.update { oldValue ->
            oldValue.copy(
                emailCorrect = oldValue.emailIsNotEmpty(),
                canRegister = oldValue.emailIsNotEmpty(),
                usernameErrorMessage = R.string.email_cant_be_empty
            )
        }
    }

    private fun checkPassword(){
        _uiState.update { oldValue ->
            oldValue.copy(
                passwordCorrect = oldValue.passwordIsNotEmpty(),
                canRegister = oldValue.passwordIsNotEmpty(),
                passwordErrorMessage = R.string.password_cant_be_empty
            )
        }
    }

    private fun checkConfirmedPassword(){
        _uiState.update { oldValue ->
            oldValue.copy(
                confirmedPasswordCorrect = oldValue.confirmedPasswordIsNotEmpty(),
                canRegister = oldValue.confirmedPasswordIsNotEmpty(),
                confirmedPasswordErrorMessage = R.string.confirmed_password_cant_be_empty
            )
        }
    }

    private fun checkPasswordLength(){
        _uiState.update { oldValue ->
            oldValue.copy(
                passwordCorrect = oldValue.passwordLengthMoreThan8(),
                canRegister = oldValue.passwordLengthMoreThan8(),
                passwordErrorMessage = R.string.your_password_less_than_8
            )
        }
    }

    private fun checkPasswordAndConfirmedPasswordAreSame(){
        _uiState.update { oldValue ->
            oldValue.copy(
                confirmedPasswordCorrect = oldValue.passwordAndConfirmedPasswordSame(),
                canRegister = oldValue.passwordAndConfirmedPasswordSame(),
                confirmedPasswordErrorMessage = R.string.password_and_confirmed_password_not_same
            )
        }
    }
}