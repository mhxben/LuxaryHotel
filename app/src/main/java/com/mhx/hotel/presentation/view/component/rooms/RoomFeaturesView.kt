package com.mhx.hotel.presentation.view.component.rooms

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mhx.hotel.data.remote.SharedPrefs
import com.mhx.hotel.presentation.view.component.CustomText
import com.mhx.hotel.presentation.view.component.StaticColumn
import com.mhx.hotel.presentation.viewmodel.GetRoomByIdViewModel
import com.mhx.hotel.ui.theme.DarkPrimary

@Composable
fun RoomFeaturesView(modifier: Modifier = Modifier) {
    val viewModel : GetRoomByIdViewModel = viewModel()
    val context = LocalContext.current
    val access = SharedPrefs.getToken(context)
    val roomId = SharedPrefs.getRoomId(context)
    LaunchedEffect(roomId){
        if(access != null && roomId != null){
            viewModel.getRoomById(access,roomId)
        }
    }
    Column(modifier = modifier) {
        CustomText("Features :" , DarkPrimary , 22)
        CustomText(viewModel.room?.features ?: "Loading" , DarkPrimary , 16)
        CustomText("Type :" , DarkPrimary , 22)
        CustomText(viewModel.room?.type ?: "Loading" , DarkPrimary , 16)
    }
}