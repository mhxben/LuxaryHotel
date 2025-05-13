package com.mhx.hotel.presentation.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mhx.hotel.data.model.InvoicePostRequest
import com.mhx.hotel.data.remote.SharedPrefs
import com.mhx.hotel.presentation.view.component.AppButton
import com.mhx.hotel.presentation.view.component.booking.TopAppBar
import com.mhx.hotel.presentation.view.component.rooms.RoomPresentation
import com.mhx.hotel.presentation.viewmodel.*

@Composable
fun PaymentPage(navController : NavController){
    val viewModel : GetRoomByIdViewModel = viewModel()
    val postInvoice : InvoicePostViewModel = viewModel()
    val getReservation  : GetReservationByIdViewModel = viewModel()
    val context = LocalContext.current
    val access = SharedPrefs.getToken(context)
    val roomId = SharedPrefs.getRoomId(context)
    val reservationId = SharedPrefs.getReservationId(context)
    LaunchedEffect(roomId){
        if(access != null && roomId != null && reservationId != null ){
            viewModel.getRoomById(access,roomId)
            getReservation.getReservation(access , reservationId)
        }

    }
    Scaffold(
        topBar = { TopAppBar(viewModel.room?.name ?:"Loading",navController) },

        bottomBar = {
            val status = getReservation.reservation?.status
            if (status == "confirmed"){
                AppButton("Pay", onClick = {
                    val clientId = getReservation.reservation?.client
                    val amount = viewModel.room?.price
                    val service = viewModel.room?.name

                    if (access != null && clientId != null && amount != null && service != null) {
                        val postRequest = InvoicePostRequest(
                            client = clientId,
                            amount = amount,
                            status = "paid",
                            services_used = service,
                            discount_code = "SUMMER2025"
                        )

                        postInvoice.postInvoice(access = access, postRequest = postRequest)
                        Toast.makeText(context , "You pay ${postInvoice.invoicePostResponse?.amount}" , Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                },)
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            RoomPresentation(
                imageUrl = viewModel.roomPic?.image ?: "",
                roomName = viewModel.room?.name ?: "Room name",
                location = "Unknown location",
                price = viewModel.room?.price ?: "0 DA",
                status = getReservation.reservation?.status ?:"wait"
            )
        }
    }
}