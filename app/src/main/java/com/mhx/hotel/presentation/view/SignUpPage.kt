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
import androidx.navigation.NavController
import com.mhx.hotel.R
import com.mhx.hotel.data.model.RegisterRequest
import com.mhx.hotel.data.model.utils.RegisterValidationInfo
import com.mhx.hotel.presentation.view.component.*
import com.mhx.hotel.presentation.view.component.model.OutlinedTextFieldClass
import com.mhx.hotel.presentation.navigation.NavigationActions
import com.mhx.hotel.ui.theme.*

@Composable
fun SignUpPage(navController: NavController){
    var registerRequest by remember { mutableStateOf(RegisterRequest("" , "" ,"","")) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val validator = RegisterValidationInfo()
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
                    label = "Full Name",
                    value = registerRequest.fullName,
                    onValueChange = {newFullName -> registerRequest = registerRequest.copy(fullName = newFullName)},
                    keyboardType = KeyboardType.Text
                )
            )
            MainOutlinedTextField(
                params = OutlinedTextFieldClass(
                    label = "Username",
                    value = registerRequest.username,
                    onValueChange = {newFullName -> registerRequest = registerRequest.copy(fullName = newFullName)},
                    keyboardType = KeyboardType.Text
                )
            )
            MainOutlinedTextField(
                params = OutlinedTextFieldClass(
                    value = registerRequest.dateOfBirth,
                    onValueChange = { registerRequest = registerRequest.copy(dateOfBirth = it) },
                    label = "Date of birth",
                    isDatePicker = true
                )
            )
            MainOutlinedTextField(
                params = OutlinedTextFieldClass(
                    label = "Password",
                    value = registerRequest.password,
                    onValueChange = { newPassword -> registerRequest = registerRequest.copy(password = newPassword) },
                    keyboardType = KeyboardType.Password,
                    isPasswordField = true,
                    isPasswordVisible = isPasswordVisible,
                    onVisibilityToggle = { isPasswordVisible = !isPasswordVisible }
                )
            )
            MainOutlinedTextField(
                params = OutlinedTextFieldClass(
                    label = "Confirm password",
                    value = registerRequest.password,
                    onValueChange = { newPassword -> registerRequest = registerRequest.copy(password = newPassword) },
                    keyboardType = KeyboardType.Password,
                    isPasswordField = true,
                    isPasswordVisible = isPasswordVisible,
                    onVisibilityToggle = { isPasswordVisible = !isPasswordVisible }
                )
            )
            AppButton("Create account ",onClick={
                val errorMessage = validator.registervalidationInfo(registerRequest)
                if (errorMessage != null){
                    Toast.makeText(context , errorMessage, Toast.LENGTH_LONG ).show()
                }else{
                    NavigationActions.navigationToHome(navController)
                    //view model
                }
            })
            StaticRow {
                PrimaryText("Have you an account ?" , DarkPrimary)
                PrimaryText(" Login" , AccentYellow , Modifier.clickable { NavigationActions.navigationToLogin(navController)})
            }
        }
    }
}