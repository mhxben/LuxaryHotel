package com.mhx.hotel.presentation.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mhx.hotel.ui.theme.*

@Composable
fun FeedBackView(
    reviewer : String,
    rating : Int,
    message : String
){
    Box(Modifier.fillMaxWidth().background(Color.White).padding(12.dp)) {
        Column(verticalArrangement = Arrangement.Top , horizontalAlignment = Alignment.Start ) {
            Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.SpaceBetween ) {
                PrimaryText(reviewer, DarkPrimary)
                PrimaryText("$rating ⭐", AccentYellow)
            }
            PrimaryText(message, DarkPrimary)
        }
    }
}