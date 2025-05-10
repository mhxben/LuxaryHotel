package com.mhx.hotel.presentation.view.component.rooms

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mhx.hotel.ui.theme.DarkPrimary

@Composable
fun RoomTabSwitcher(
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    val tabs = listOf("Features", "Feedbacks")

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(50))
            .height(45.dp)
    ) {
        tabs.forEach { tab ->
            val isSelected = selectedTab == tab
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(50))
                    .background(if (isSelected) DarkPrimary else Color.Transparent)
                    .clickable { onTabSelected(tab) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tab,
                    color = if (isSelected) Color.White else Color.Gray
                )
            }
        }
    }
}
