package com.mhx.hotel.presentation.view.component.rooms

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mhx.hotel.data.remote.SharedPrefs
import com.mhx.hotel.presentation.view.component.FeedBackView
import com.mhx.hotel.presentation.view.component.PrimaryText
import com.mhx.hotel.presentation.viewmodel.GetRoomReviewsViewModel
import com.mhx.hotel.ui.theme.DarkPrimary

@Composable
fun RoomFeedbacksView(modifier: Modifier = Modifier) {
    val viewModel: GetRoomReviewsViewModel = viewModel()
    val context = LocalContext.current
    val access = SharedPrefs.getToken(context)
    val roomId = SharedPrefs.getRoomId(context)

    LaunchedEffect(access, roomId) {
        if (access != null && roomId != null) {
            viewModel.getRoomReviews(access , roomId)
        }
    }

    viewModel.reviews?.let { list ->
        LazyColumn(modifier = modifier) {
            items(list) { review ->
                FeedBackView(
                    reviewer = review.reviewer.toString(),
                    rating = review.rating,
                    message = review.comment
                )
            }
        }
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            PrimaryText("Wait" , DarkPrimary)
        }
    }
}
