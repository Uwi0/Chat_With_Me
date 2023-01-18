package com.kakapo.authentication.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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
import com.kakapo.authentication.register.navigation.navigateToRegisterScreen
import com.kakapo.home.navigation.HOME_ROUTE
import com.kakapo.ui.ScreenNotImplementedYet
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
    LaunchedEffect(key1 = uiState.canLogin) {
        if (uiState.canLogin) {
            navController.navigate(HOME_ROUTE) {
                popUpTo(LOGIN_ROUTE) {
                    inclusive = true
                    saveState = false
                }
            }
        }
    }
    if (contentType == CWMContentType.DUAL_PANE) {
        TwoPane(
            first = {
                Row(Modifier.padding(horizontal = 150.dp)) {
                    LoginScreen(viewModel, uiState, navController)
                }
            },
            second = { ScreenNotImplementedYet(screenTitle = "Login side panel") },
            strategy = HorizontalTwoPaneStrategy(splitFraction = 0.5f, gapWidth = 16.dp),
            displayFeatures = displayFeature
        )
    } else {
        LoginScreen(viewModel, uiState, navController)
    }
}

@Composable
internal fun LoginScreen(
    viewModel: LoginViewModel,
    uiState: LoginContract.UiState,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.loggin))
                append(" ")
                append(stringResource(id = com.kakapo.designsystem.R.string.app_name))
            },
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(36.dp))
        LoginForm(uiState, viewModel)
        Spacer(modifier = Modifier.height(32.dp))
        ButtonLarge(
            onClick = viewModel::loginApp,
            textButton = R.string.loggin
        )
        Spacer(modifier = Modifier.height(16.dp))
        ButtonLarge(
            onClick = {},
            textButton = R.string.login_with_google,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = stringResource(
                        id = R.string.login_with_google
                    )
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.clickable { navController.navigateToRegisterScreen() },
            text = buildAnnotatedString {
            append(stringResource(id = R.string.dont_have_account))
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)){
                append(" ")
                append(stringResource(id = R.string.register))
            }
        })
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