package com.mhx.hotel.presentation.view.component.rooms

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mhx.hotel.data.remote.SharedPrefs
import com.mhx.hotel.presentation.navigation.NavigationActions
import com.mhx.hotel.presentation.view.component.*
import com.mhx.hotel.ui.theme.*

@Composable
fun RoomCard(
    roomId:Int,
    roomName: String,
    location: String,
    owner: String,
    price:String,
    imageUrl: String,
    navController: NavController
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp),
                clip = true
            )
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .clickable {
                NavigationActions.navigationToBooking(navController = navController)
                SharedPrefs.saveRoomId(context ,roomId )
            }
    ) {
        Column(horizontalAlignment = Alignment.End , verticalArrangement = Arrangement.Top) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            )
            Column(horizontalAlignment = Alignment.Start , verticalArrangement = Arrangement.Top , modifier = Modifier.padding(16.dp)) {
                Row(horizontalArrangement = Arrangement.SpaceBetween , verticalAlignment = Alignment.CenterVertically ) {
                    CustomText(roomName , DarkPrimary ,22 )

                }
                CustomRow {
                    Icon(
                        Icons.Outlined.LocationCity,
                        "",
                        tint = AccentYellow
                    )
                    PrimaryText(location , DarkPrimary)
                }
                CustomRow {
                    Icon(
                        Icons.Outlined.Person,
                        "",
                        tint = AccentYellow
                    )
                    PrimaryText(owner , DarkPrimary)
                }
            }
        }
    }
}