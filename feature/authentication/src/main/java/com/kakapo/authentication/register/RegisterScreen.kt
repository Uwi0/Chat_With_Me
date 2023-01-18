package com.kakapo.authentication.register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.window.layout.DisplayFeature
import com.kakapo.authentication.R
import com.kakapo.authentication.component.TextInputDefault
import com.kakapo.authentication.component.TextInputPassword
import com.kakapo.authentication.login.navigation.navigateToLogin
import com.kakapo.authentication.register.navigation.REGISTER_ROUTE
import com.kakapo.ui.button.ButtonLarge
import com.kakapo.ui.utils.CWMContentType
import com.kakapo.ui.utils.CWMNavigationType

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun RegisterRoute(
    contentType: CWMContentType,
    navigationType: CWMNavigationType,
    navController: NavHostController,
    displayFeature: List<DisplayFeature>,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = uiState.canRegister){
        if (uiState.canRegister){
            navController.navigateToLogin(startDestination = REGISTER_ROUTE)
        }
    }
    RegisterScreen(uiState, viewModel, navController)
}

@Composable
internal fun RegisterScreen(
    uiState: RegisterUIState,
    viewModel: RegisterViewModel,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.align(CenterHorizontally),
            text = buildAnnotatedString {
                append(stringResource(id = R.string.register))
                append(" ")
                append(stringResource(id = com.kakapo.designsystem.R.string.app_name))
            },
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(24.dp))
        RegisterFrom(uiState = uiState, viewModel = viewModel)
        Spacer(modifier = Modifier.height(24.dp))
        ButtonLarge(
            modifier = Modifier.align(CenterHorizontally),
            onClick = viewModel::validateRegisterFrom, textButton = R.string.register)
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            modifier = Modifier
                .width(126.dp)
                .align(CenterHorizontally),
            onClick = {
                navController.navigateToLogin(startDestination = REGISTER_ROUTE)
            }
        ){
            Text(
                text = stringResource(id = R.string.loggin),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
private fun RegisterFrom(uiState: RegisterUIState, viewModel: RegisterViewModel) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TextInputDefault(
            query = uiState.name,
            onQueryChanged = viewModel::getName,
            hint = R.string.name
        )
        TextInputDefault(
            query = uiState.username,
            onQueryChanged = viewModel::getUsername,
            hint = R.string.username
        )
        TextInputDefault(
            query = uiState.email,
            onQueryChanged = viewModel::getEmail,
            hint = R.string.email,
            keyboardType = KeyboardType.Email
        )
        TextInputPassword(
            query = uiState.password,
            onQueryChanged = viewModel::getPassword
        )
        TextInputPassword(
            query = uiState.confirmedPassword,
            onQueryChanged = viewModel::getConfirmedPassword,
            hint = R.string.confirm_password
        )
    }
}