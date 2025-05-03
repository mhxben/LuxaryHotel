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
    var registerRequest by remember { mutableStateOf(RegisterRequest("" ,"", "client" , "")) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var confirmPassword by remember{ mutableStateOf("") }
    val validator = RegisterValidationInfo()
    val context = LocalContext.current
    val viewModel : RegisterViewModel = viewModel()
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
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it},
                    keyboardType = KeyboardType.Password,
                    isPasswordField = true,
                    isPasswordVisible = isPasswordVisible,
                    onVisibilityToggle = { isPasswordVisible = !isPasswordVisible }
                )
            )
            AppButton("Create account ",onClick={
                val errorMessage = validator.registervalidationInfo(registerRequest)
                if (confirmPassword != registerRequest.password){
                    Toast.makeText(context,"Please enter a match password" , Toast.LENGTH_LONG).show()
                }else if (errorMessage != null){
                    Toast.makeText(context , errorMessage, Toast.LENGTH_LONG ).show()
                }else{
                    viewModel.register(registerRequest)
                }
            })
            LaunchedEffect(viewModel.registerResponse to viewModel.errorMessage){
                viewModel.registerResponse?.let { response ->
                    Toast.makeText(context,"Your creating done ${response.username}" , Toast.LENGTH_LONG).show()
                    NavigationActions.navigationToLogin(navController)
                    viewModel.registerResponse = null
                }
                viewModel.errorMessage?.let { errorMessage ->
                    Toast.makeText(context,errorMessage,Toast.LENGTH_LONG).show()
                    viewModel.errorMessage = null
                }
            }
            StaticRow {
                PrimaryText("Have you an account ?" , DarkPrimary)
                PrimaryText(" Login" , AccentYellow , Modifier.clickable { NavigationActions.navigationToLogin(navController)})
            }
        }
    }
}