package com.mhx.hotel.presentation.view

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mhx.hotel.presentation.view.component.model.OutlinedTextFieldClass
import com.mhx.hotel.R
import com.mhx.hotel.data.model.LoginRequest
import com.mhx.hotel.data.model.utils.LoginValidateInfo
import com.mhx.hotel.presentation.navigation.NavigationActions
import com.mhx.hotel.presentation.view.component.*
import com.mhx.hotel.presentation.viewmodel.LoginViewModel
import com.mhx.hotel.ui.theme.*

@Composable
fun LoginPage(navController: NavController) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    var loginRequest by remember { mutableStateOf(LoginRequest("",""))}
    val validator = LoginValidateInfo()
    val context = LocalContext.current
    val viewModel : LoginViewModel = viewModel()

    StaticColumn {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo"
            , Modifier.size(200.dp)
        )
        CustomText("Welcome to Luxury Hotel" , AccentYellow , 22)
        PrimaryText("A booking platform" , AccentYellow)
        MainOutlinedTextField(
            params = OutlinedTextFieldClass(
                label = "Email",
                value = loginRequest.email,
                onValueChange = {newEmail -> loginRequest = loginRequest.copy(email = newEmail)},
                keyboardType = KeyboardType.Email)
        )
        MainOutlinedTextField(
            params = OutlinedTextFieldClass(
                label = "Password",
                value = loginRequest.password,
                onValueChange = { newPassword -> loginRequest = loginRequest.copy(password = newPassword) },
                keyboardType = KeyboardType.Password,
                isPasswordField = true,
                isPasswordVisible = isPasswordVisible,
                onVisibilityToggle = { isPasswordVisible = !isPasswordVisible }
            )
        )
        AppButton("Login",onClick = {
            val errorMessage =  validator.validateLoginInfos(loginRequest)
            if ( errorMessage != null){
                Toast.makeText(context,errorMessage,Toast.LENGTH_LONG).show()
            }else{
                viewModel.login(context , loginRequest)
            }
        })
        LaunchedEffect(viewModel.loginResponse) {
            viewModel.loginResponse?.let {
                NavigationActions.navigationToHome(navController)
            }
        }
        LaunchedEffect(viewModel.errorMessage) {
            viewModel.errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
        StaticRow {
            PrimaryText("Haven't you an account ?" , DarkPrimary)
            PrimaryText(" Create a one" , AccentYellow , Modifier.clickable { NavigationActions.navigationToSignUp(navController)})
        }
    }
}