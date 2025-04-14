package com.mhx.hotel.presentation.view.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun PrimaryText(text : String , color: Color , modifier: Modifier = Modifier){
    Text(text,
        color = color,
        fontSize = 16.sp,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier)
}

@Composable
fun SecondaryText(text : String , color: Color , modifier: Modifier = Modifier){
    Text(text,
        color = color,
        fontSize = 14.sp,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier)
}

@Composable
fun CustomText(text: String , color: Color ,size : Int, modifier: Modifier = Modifier){
    Text(text,
        color = color,
        fontSize = size.sp,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier)
}