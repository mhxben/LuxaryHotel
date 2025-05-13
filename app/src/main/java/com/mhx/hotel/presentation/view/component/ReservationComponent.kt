package com.mhx.hotel.presentation.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mhx.hotel.data.remote.SharedPrefs
import com.mhx.hotel.ui.theme.DarkPrimary

@Composable
fun ReservationComponent(
    reservationId : Int ,
    imageUrl: String,
    roomName: String,
    startDate: String,
    endDate: String,
    state: String,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(120.dp)
            .shadow(4.dp, shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp))
            .background(
                color = androidx.compose.ui.graphics.Color.White,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
            )
            .clickable {
                SharedPrefs.saveReservationId(context,reservationId)
                onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight()
            ) {
                PrimaryText(roomName, DarkPrimary)
                PrimaryText("From: $startDate", DarkPrimary)
                PrimaryText("To: $endDate", DarkPrimary)
                PrimaryText("Status: $state", DarkPrimary)
            }
        }
    }
}
