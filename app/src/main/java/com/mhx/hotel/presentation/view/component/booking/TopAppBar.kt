package com.mhx.hotel.presentation.view.component.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
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
import androidx.navigation.NavController
import com.mhx.hotel.data.remote.SharedPrefs
import com.mhx.hotel.ui.theme.DarkPrimary

@Composable
fun TopAppBar(navController : NavController) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(DarkPrimary)
            .clickable {
                SharedPrefs.clearRoomId(context)
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
}
