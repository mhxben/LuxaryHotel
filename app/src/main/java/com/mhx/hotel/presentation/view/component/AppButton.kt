package com.mhx.hotel.presentation.view.component

import androidx.compose.foundation.border
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mhx.hotel.ui.theme.*

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    color: Color = AccentYellow,
    textColor: Color = DarkPrimary,
    hasBorder: Boolean = false,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = if (hasBorder) Color.Transparent else color),
        shape = shape,
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .then(
                if (hasBorder) Modifier.border(1.dp, color, shape) else Modifier
            )
    ) {
        PrimaryText(text = text, color = textColor)
    }
}