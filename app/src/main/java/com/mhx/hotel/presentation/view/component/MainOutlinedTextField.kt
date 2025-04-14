package com.mhx.hotel.presentation.view.component

import android.app.DatePickerDialog
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import com.mhx.hotel.presentation.view.component.model.OutlinedTextFieldClass
import com.mhx.hotel.ui.theme.*
import java.util.*

@Composable
fun MainOutlinedTextField(params: OutlinedTextFieldClass) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // DatePicker dialog
    val datePicker = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val formatted = "%04d-%02d-%02d".format(year, month + 1, dayOfMonth)
                params.onValueChange(formatted)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    val interactionSource = remember { MutableInteractionSource() }

    OutlinedTextField(
        value = params.value,
        onValueChange = { params.onValueChange(it) },
        label = { Text(text = params.label) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        readOnly = params.isDropdown || params.isDatePicker,
        interactionSource = interactionSource,
        keyboardOptions = KeyboardOptions(keyboardType = params.keyboardType),
        visualTransformation = if (params.isPasswordField && !params.isPasswordVisible)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        trailingIcon = {
            when {
                params.isPasswordField && params.onVisibilityToggle != null -> {
                    val icon = if (params.isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = params.onVisibilityToggle) {
                        Icon(imageVector = icon, contentDescription = "Toggle Password Visibility")
                    }
                }

                params.isDropdown -> {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Filled.ArrowDropDown, contentDescription = "Expand Dropdown")
                    }
                }
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AccentYellow,
            unfocusedBorderColor = AccentYellow.copy(alpha = 0.5f),
            focusedLabelColor = AccentYellow,
            cursorColor = AccentYellow
        )
    )

    // Handle interaction for click events
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            if (interaction is PressInteraction.Release) {
                when {
                    params.isDatePicker -> datePicker.show()
                    params.isDropdown -> expanded = true
                }
            }
        }
    }

    // Dropdown menu
    if (params.isDropdown) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            params.dropdownItems.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        params.onValueChange(item)
                        expanded = false
                    }
                )
            }
        }
    }
}