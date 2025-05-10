package com.mhx.hotel.presentation.view.component.rooms

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mhx.hotel.presentation.view.component.*
import com.mhx.hotel.ui.theme.*

@Composable
fun RoomPresentation(
    imageUrl : String,
    roomName:String,
    location : String,
    price : String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(20.dp))
        )
        CustomText(roomName , DarkPrimary , 22 , Modifier.align(Alignment.Start))
        CustomRow {
            Icon(
                imageVector = Icons.Outlined.LocationCity,
                contentDescription = null,
                tint = AccentYellow,
            )
            PrimaryText(location , DarkPrimary )
        }
        CustomRow {
            Icon(
                imageVector = Icons.Outlined.PriceCheck,
                contentDescription = null,
                tint = AccentYellow,
            )
            PrimaryText(price , DarkPrimary )
        }
        RoomDetailsScreen()
    }
}