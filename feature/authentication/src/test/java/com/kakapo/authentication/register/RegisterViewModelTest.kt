package com.kakapo.authentication.register

import org.junit.Assert.*
import org.junit.Test

class RegisterViewModelTest{

    private val viewModel = RegisterViewModel()

    @Test
    fun `user input name and it contain value`(){
        viewModel.getName(NAME)
        val registerUiState = viewModel.uiState.value
        assertEquals(NAME, registerUiState.name)
    }

    @Test
    fun `user input username and it contain value`(){
        viewModel.getUsername(USERNAME)
        val registerUIState = viewModel.uiState.value
        assertEquals(USERNAME, registerUIState.username)
    }

    @Test
    fun `user input email and it contain value`(){
        viewModel.getEmail(EMAIL)
        val registerUIState = viewModel.uiState.value
        assertEquals(EMAIL, registerUIState.email)
    }

    @Test
    fun `user input password and it contain value`(){
        viewModel.getPassword(PASSWORD)
        val registerUIState = viewModel.uiState.value
        assertEquals(PASSWORD, registerUIState.password)
    }

    @Test
    fun `user input confirmed password and it contain value`(){
        viewModel.getConfirmedPassword(CONFIRMED_PASSWORD)
        val registerUIState = viewModel.uiState.value
        assertEquals(CONFIRMED_PASSWORD, registerUIState.confirmedPassword)
    }

    @Test
    fun `if form is empty user can't register`(){
        viewModel.getName("")
        viewModel.getUsername("")
        viewModel.getEmail("")
        viewModel.getPassword("")
        viewModel.getConfirmedPassword("")
        viewModel.validateRegisterFrom()
        val registerUIState = viewModel.uiState.value
        assertFalse(registerUIState.nameIsNotEmpty())
        assertFalse(registerUIState.usernameIsNotEmpty())
        assertFalse(registerUIState.emailIsNotEmpty())
        assertFalse(registerUIState.passwordIsNotEmpty())
        assertFalse(registerUIState.confirmedPasswordIsNotEmpty())
        assertFalse(registerUIState.canRegister)
    }

    @Test
    fun `if form is not empty user can register`(){
        viewModel.getName(NAME)
        viewModel.getUsername(USERNAME)
        viewModel.getEmail(EMAIL)
        viewModel.getPassword(PASSWORD)
        viewModel.getConfirmedPassword(CONFIRMED_PASSWORD)
        viewModel.validateRegisterFrom()
        val registerUIState = viewModel.uiState.value
        assertTrue(registerUIState.nameIsNotEmpty())
        assertTrue(registerUIState.usernameIsNotEmpty())
        assertTrue(registerUIState.passwordIsNotEmpty())
        assertTrue(registerUIState.confirmedPasswordIsNotEmpty())
        assertTrue(registerUIState.canRegister)
    }

    companion object{
        private const val NAME = "dwi"
        private const val USERNAME = "uwi0"
        private const val EMAIL = "uwi0@gmail.com"
        private const val PASSWORD = "123456789"
        private const val CONFIRMED_PASSWORD = PASSWORD
    }
}