package com.hnrylvo.immomarketapp.ux.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hnrylvo.immomarketapp.R
import com.hnrylvo.immomarketapp.ui.theme.LightGray
import com.hnrylvo.immomarketapp.ui.theme.MyTypography
import com.hnrylvo.immomarketapp.ui.theme.PrimaryGreen
import com.hnrylvo.immomarketapp.ui.theme.SecondaryGreen
import com.hnrylvo.immomarketapp.ui.theme.White
import com.hnrylvo.inmomarket.R
import com.hnrylvo.inmomarket.ui.compose.buttons.PrimaryButton
import com.hnrylvo.inmomarket.ui.compose.containers.AppScaffold
import com.hnrylvo.inmomarket.ui.compose.dividers.HorizontalDivider
import com.hnrylvo.inmomarket.ui.compose.headers.TitleHeader
import com.hnrylvo.inmomarket.ui.compose.inputs.InputField
import com.hnrylvo.inmomarket.ui.compose.inputs.PasswordInputField
import com.hnrylvo.inmomarket.ui.theme.BackgroundGreen
import com.hnrylvo.inmomarket.ui.theme.LightGray
import com.hnrylvo.inmomarket.ui.theme.MyTypography
import com.hnrylvo.inmomarket.ui.theme.PrimaryGreen
import com.hnrylvo.inmomarket.ui.theme.SecondaryGreen
import com.hnrylvo.inmomarket.ui.theme.White
import com.hnrylvo.inmomarket.ux.register.RegisterRoute
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel = LoginViewModel(context, navController)
    AppScaffold {
        Login(viewModel, navController)
    }
}

@Composable
fun Login(viewModel: LoginViewModel, navController: NavController) {

    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val enableLogin by viewModel.loginEnabled.collectAsState()
    val snackBarMessage by viewModel.snackBarMessage.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(snackBarMessage) {
        snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackBarHostState.showSnackbar(message)
                viewModel.clearSnackBarMessage()
            }
        }
    }

    Column {
        Header(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(60.dp))
        EmailTextField(email) { viewModel.onValueChange(it, password) }
        Spacer(modifier = Modifier.padding(16.dp))
        PasswordTextField(password) { viewModel.onValueChange(email, it) }
        Spacer(modifier = Modifier.padding(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.padding(45.dp))
        LoginButton(
            enabled = enableLogin,
            onClick = { viewModel.login() }
        )
        Spacer(modifier = Modifier.padding(16.dp))
        OrSection()
        Spacer(modifier = Modifier.padding(16.dp))
        GoogleButton()
        Spacer(modifier = Modifier.padding(16.dp))
        DontHaveAccount(Modifier.align(Alignment.CenterHorizontally), navController)
        CustomSnackBar(
            snackBarHostState = snackBarHostState,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun CustomSnackBar(
    snackBarHostState : SnackbarHostState,
    modifier: Modifier = Modifier
) {
    SnackbarHost(
        hostState = snackBarHostState,
        snackbar = {snackBarData ->
            Snackbar(
                snackbarData = snackBarData,
                contentColor = Color.White,
                containerColor = LightGray,
                actionColor = SecondaryGreen,
                shape = RoundedCornerShape(8.dp),
                modifier = modifier
            )
        }
    )
}

@Composable
fun DontHaveAccount(modifier: Modifier, navController: NavController) {
    Row(modifier = modifier){
        Text(
            text = stringResource(id = R.string.login_dont_have_account),
            style = MyTypography.labelSmall,
            color = SecondaryGreen
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            modifier = Modifier.clickable {
                navController.navigate(route = RegisterRoute.route)
            },
            text = stringResource(id = R.string.login_create_account),
            style = MyTypography.titleSmall,
            color = PrimaryGreen
        )
    }
}

@Composable
fun GoogleButton() {
    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(containerColor = White),
        shape = RoundedCornerShape(16.dp),
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = stringResource(id = R.string.login_google_icon)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = stringResource(id = R.string.login_google),
            style = MyTypography.titleMedium,
            color = PrimaryGreen
        )
    }
}

@Composable
fun OrSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalDivider(maxWidth = 0.4f)
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.login_or),
            style = MyTypography.labelLarge,
            color = SecondaryGreen
        )
        HorizontalDivider(maxWidth = 0.8f)
    }
}

@Composable
fun LoginButton(
    enabled: Boolean,
    onClick: () -> Unit
) {
    PrimaryButton(
        buttonText = R.string.login_login,
        onClick = onClick,
        enabled = enabled
    )
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.login_forgot_password),
        style = MyTypography.titleSmall,
        color = SecondaryGreen
    )
}

@Composable
fun PasswordTextField(password: String, onValueChange: (String) -> Unit) {
    PasswordInputField(
        value = password,
        placeholderId = R.string.login_password,
        keyboardType = KeyboardType.Password,
        onValueChange = {onValueChange(it)},
    )
}

@Composable
fun EmailTextField(email: String, onValueChange: (String) -> Unit) {
    InputField(
        value = email,
        placeholderId = R.string.login_email,
        keyboardType = KeyboardType.Email,
        onValueChange = {onValueChange(it)}
    )
}

@Composable
fun Header(modifier: Modifier) {
    TitleHeader(
        modifier = modifier,
        headerText = R.string.login_header
    )
}

@Composable
@Preview(showSystemUi = true)
fun LoginScreenPreview() {
    LoginScreen(navController = NavController(LocalContext.current))
}
