package com.mhx.hotel.presentation.view.component.booking

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mhx.hotel.presentation.view.component.*
import com.mhx.hotel.ui.theme.DarkPrimary

@Composable
fun TopAppBar(roomName : String ,navController : NavController) {
    val context = LocalContext.current
    CustomRow {
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(DarkPrimary)
                .clickable {
                    navController.navigateUp()
                }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        CustomText(roomName, DarkPrimary, 22)
    }
}
@Preview
@Composable
fun TopAppBarPreview() {
    TopAppBar("hello",navController = rememberNavController())
}