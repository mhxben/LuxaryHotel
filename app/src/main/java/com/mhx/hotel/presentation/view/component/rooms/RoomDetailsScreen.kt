package com.mhx.hotel.presentation.view.component.rooms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf

@Composable
fun RoomDetailsScreen() {
    var selectedTab by remember { mutableStateOf("Features") }

    Column(modifier = Modifier.fillMaxSize()) {
        RoomTabSwitcher(selectedTab) { selectedTab = it }

        when (selectedTab) {
            "Features" -> RoomFeaturesView()
            "Feedbacks" -> RoomFeedbacksView(modifier = Modifier.weight(1f))
        }
    }
}
