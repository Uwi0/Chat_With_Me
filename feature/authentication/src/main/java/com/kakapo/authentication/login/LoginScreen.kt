package com.kakapo.authentication.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.window.layout.DisplayFeature
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane
import com.kakapo.authentication.R
import com.kakapo.authentication.component.TextInputDefault
import com.kakapo.authentication.component.TextInputPassword
import com.kakapo.authentication.login.navigation.LOGIN_ROUTE
import com.kakapo.home.navigation.HOME_ROUTE
import com.kakapo.ui.button.ButtonLarge
import com.kakapo.ui.utils.CWMContentType
import com.kakapo.ui.utils.CWMNavigationType

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun LoginRoute(
    contentType: CWMContentType,
    navigationType: CWMNavigationType,
    navController: NavHostController,
    displayFeature: List<DisplayFeature>,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = uiState.canLogin){
        if (uiState.canLogin){
            navController.navigate(HOME_ROUTE) {
                popUpTo(LOGIN_ROUTE) {
                    inclusive = true
                    saveState = false
                }
            }
        }
    }
    if (contentType == CWMContentType.DUAL_PANE){
        TwoPane(
            first = { LoginScreen(viewModel, uiState) },
            second = {  },
            strategy = HorizontalTwoPaneStrategy(splitFraction = 0.5f, gapWidth = 16.dp),
            displayFeatures = displayFeature
        )
    }else{
        LoginScreen(viewModel, uiState)
    }
}

@Composable
internal fun LoginScreen(
    viewModel: LoginViewModel,
    uiState: LoginContract.UiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = com.kakapo.designsystem.R.string.app_name),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(36.dp))
        LoginForm(uiState, viewModel)
        Spacer(modifier = Modifier.height(32.dp))
        ButtonLarge(
            onClick = viewModel::loginApp,
            textButton = R.string.loggin
        )
    }
}

@Composable
private fun LoginForm(
    uiState: LoginContract.UiState,
    viewModel: LoginViewModel
) {
    TextInputDefault(
        query = uiState.username,
        onQueryChanged = viewModel::getUsername,
        hint = R.string.username
    )
    Spacer(modifier = Modifier.height(24.dp))
    TextInputPassword(
        query = uiState.password,
        onQueryChanged = viewModel::getPassword,
    )
}