package com.mhx.hotel.presentation.view

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mhx.hotel.data.model.ReservationRequest
import com.mhx.hotel.data.remote.SharedPrefs
import com.mhx.hotel.presentation.view.component.AppButton
import com.mhx.hotel.presentation.view.component.MainOutlinedTextField
import com.mhx.hotel.presentation.view.component.StaticColumn
import com.mhx.hotel.presentation.view.component.booking.TopAppBar
import com.mhx.hotel.presentation.view.component.model.OutlinedTextFieldClass
import com.mhx.hotel.presentation.viewmodel.ReservationsViewModel

@Composable
fun ReservationFormulaireView(navController: NavController){
    val context = LocalContext.current
    val clientId = SharedPrefs.getUserId(context) ?: 0
    val roomId = SharedPrefs.getRoomId(context) ?: 0
    val access = SharedPrefs.getToken(context)
    val viewModel : ReservationsViewModel = viewModel()

    var reservationRequest by remember { mutableStateOf(ReservationRequest(clientId , "" ,roomId , "" , "pending" ,"room")) }
    Scaffold(
        topBar = {
            TopAppBar("Reservation",navController)
        }
    ) {padding->
        StaticColumn(modifier = Modifier.padding(padding)) {
            MainOutlinedTextField(
                OutlinedTextFieldClass(
                    label = "Start Date",
                    value = reservationRequest.start_date,
                    onValueChange = { reservationRequest = reservationRequest.copy(start_date = it) },
                    isDatePicker = true
                )
            )

            MainOutlinedTextField(
                OutlinedTextFieldClass(
                    label = "End Date",
                    value = reservationRequest.end_date,
                    onValueChange = { reservationRequest = reservationRequest.copy(end_date = it) },
                    isDatePicker = true
                )
            )
            MainOutlinedTextField(
                OutlinedTextFieldClass(
                    label = "Type",
                    value = reservationRequest.type,
                    onValueChange = { reservationRequest = reservationRequest.copy(type = it) },
                    isDropdown = true,
                    dropdownItems = listOf("room", "restaurant")
                )
            )
            AppButton(
                "Confirm",onClick = {
                    access?.let {
                        viewModel.postReservation(access = access , request = reservationRequest)
                    } ?: run {
                        Toast.makeText(context, "Access token is null", Toast.LENGTH_SHORT).show()
                    }
                }
            )
            LaunchedEffect(viewModel.reservationResponse , viewModel.errorMessage){
                viewModel.reservationResponse?.let {
                    navController.popBackStack()
                    Toast.makeText(context,"Reservation is success",Toast.LENGTH_LONG).show()
                }
                viewModel.errorMessage?.let { error ->
                    Toast.makeText(context,"Reservation error : ${error}",Toast.LENGTH_LONG).show()
                }
            }
            AppButton(
                text = "Cancel",
                onClick = { navController.popBackStack()},
                hasBorder = true
            )
        }
    }
}