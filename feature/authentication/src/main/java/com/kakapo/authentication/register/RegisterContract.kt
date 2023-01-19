package com.kakapo.authentication.register

import com.kakapo.authentication.R


data class RegisterUIState(
    val name: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmedPassword: String = "",
    val nameCorrect: Boolean = false,
    val usernameCorrect: Boolean = false,
    val emailCorrect: Boolean = false,
    val passwordCorrect: Boolean = false,
    val confirmedPasswordCorrect: Boolean = false,
    val passwordAndConfirmedPasswordAreSame: Boolean = false,
    val nameErrorMessage: Int = R.string.unknown_error,
    val usernameErrorMessage: Int = R.string.unknown_error,
    val emailErrorMessage: Int = R.string.unknown_error,
    val passwordErrorMessage: Int = R.string.unknown_error,
    val confirmedPasswordErrorMessage: Int = R.string.unknown_error,
    val canRegister: Boolean = false
) {

    fun nameIsNotEmpty() = name != ""

    fun usernameIsNotEmpty() = username != ""

    fun emailIsNotEmpty() = email != ""

    fun passwordIsNotEmpty() = password != ""

    fun confirmedPasswordIsNotEmpty() = confirmedPassword != ""

    fun passwordAndConfirmedPasswordSame() = password == confirmedPassword

    fun passwordLengthMoreThan8() = password.length > 8

    fun userCanRegister() = nameCorrect &&
            usernameCorrect &&
            emailCorrect &&
            passwordCorrect &&
            confirmedPasswordCorrect
}