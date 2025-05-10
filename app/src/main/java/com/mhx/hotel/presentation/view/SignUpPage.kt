package com.mhx.hotel.presentation.view

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mhx.hotel.R
import com.mhx.hotel.data.model.RegisterRequest
import com.mhx.hotel.data.model.utils.RegisterValidationInfo
import com.mhx.hotel.presentation.view.component.*
import com.mhx.hotel.presentation.view.component.model.OutlinedTextFieldClass
import com.mhx.hotel.presentation.navigation.NavigationActions
import com.mhx.hotel.presentation.viewmodel.RegisterViewModel
import com.mhx.hotel.ui.theme.*

@Composable
fun SignUpPage(navController: NavController){
    var registerRequest by remember { mutableStateOf(RegisterRequest("" ,"", "" , "" , "" ,"client" , "")) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val validator = RegisterValidationInfo()
    val viewModel : RegisterViewModel = viewModel()
    val context = LocalContext.current
    StaticColumn {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo"
            , Modifier.size(200.dp)
        )
        CustomText("Welcome to Luxury Hotel" , AccentYellow , 22)
        PrimaryText("A booking platform" , AccentYellow)
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp))
        {
            MainOutlinedTextField(
                params = OutlinedTextFieldClass(
                    label = "First Name",
                    value = registerRequest.first_name,
                    onValueChange = {newFirstName -> registerRequest = registerRequest.copy(first_name = newFirstName)},
                    keyboardType = KeyboardType.Text
                )
            )
            MainOutlinedTextField(
                params = OutlinedTextFieldClass(
                    label = "Last Name",
                    value = registerRequest.last_name,
                    onValueChange = {newLastName -> registerRequest = registerRequest.copy(last_name = newLastName)},
                    keyboardType = KeyboardType.Text
                )
            )
            MainOutlinedTextField(
                params = OutlinedTextFieldClass(
                    label = "Username",
                    value = registerRequest.username,
                    onValueChange = {newUsername -> registerRequest = registerRequest.copy(username = newUsername)},
                    keyboardType = KeyboardType.Text
                )
            )
            MainOutlinedTextField(
                params = OutlinedTextFieldClass(
                    value = registerRequest.email,
                    onValueChange = { registerRequest = registerRequest.copy(email = it) },
                    label = "Email",
                    keyboardType = KeyboardType.Email
                )
            )
            MainOutlinedTextField(
                params = OutlinedTextFieldClass(
                    label = "Password",
                    value = registerRequest.password,
                    onValueChange = {newPassword -> registerRequest = registerRequest.copy(password = newPassword)},
                    keyboardType = KeyboardType.Text
                )
            )

            MainOutlinedTextField(
                params = OutlinedTextFieldClass(
                    label = "Confirm Password",
                    value = registerRequest.confirm_password,
                    onValueChange = { newConfirmPassword -> registerRequest = registerRequest.copy(confirm_password = newConfirmPassword)},
                    keyboardType = KeyboardType.Password,
                    isPasswordField = true,
                    isPasswordVisible = isPasswordVisible,
                    onVisibilityToggle = { isPasswordVisible = !isPasswordVisible }
                )
            )
            AppButton("Create account ",onClick={
                val errorMessage = validator.registerValidationInfo(registerRequest)
                if (errorMessage != null){
                    Toast.makeText(context , errorMessage, Toast.LENGTH_LONG ).show()
                }else{
                    viewModel.register(registerRequest)
                }
            })
            LaunchedEffect(viewModel.registerResponse) {
                viewModel.registerResponse?.let {
                    Toast.makeText(context, "Account created successfully!", Toast.LENGTH_LONG).show()
                    NavigationActions.navigationToLogin(navController)
                }
            }
            LaunchedEffect(viewModel.errorMessage) {
                viewModel.errorMessage?.let {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                }
            }
            StaticRow {
                PrimaryText("Have you an account ?" , DarkPrimary)
                PrimaryText(" Login" , AccentYellow , Modifier.clickable { NavigationActions.navigationToLogin(navController)})
            }
        }
    }
}