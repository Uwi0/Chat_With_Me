package com.kakapo.authentication.login

class LoginContract {

    data class UiState(
        val username: String = "",
        val password: String = "",
        val isUsernameInCorrect: Boolean = false,
        val isPasswordInCorrect: Boolean = false,
        val canLogin: Boolean = false
    ){
        fun allFormValid() = !isUsernameInCorrect && !isPasswordInCorrect
    }
}