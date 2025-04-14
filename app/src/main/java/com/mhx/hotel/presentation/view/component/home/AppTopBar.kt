package com.mhx.hotel.presentation.view.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mhx.hotel.R
import com.mhx.hotel.presentation.view.component.PrimaryText
import com.mhx.hotel.ui.theme.DarkPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    fullName: String,
    onLogout:() -> Unit
) {
    TopAppBar(
        title = {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painterResource(id = R.drawable.appicon),
                        "User Pic",
                        modifier = Modifier.size(40.dp).clip(CircleShape)
                    )
                    Spacer(Modifier.width(8.dp))
                    Column {
                        PrimaryText("Welcome to our platform" , DarkPrimary)
                        PrimaryText("Hi, $fullName", DarkPrimary)
                    }
                }
                IconButton(onClick = onLogout) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Logout"
                    )
                }
            }
        }
    )
}