package com.mhx.hotel.presentation.view.component.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mhx.hotel.presentation.navigation.NavigationRoutes
import com.mhx.hotel.presentation.view.component.model.BottomNavItem

@Composable
fun AppButtonBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(NavigationRoutes.Home, "Home", Icons.Default.Home),
        BottomNavItem(NavigationRoutes.Bookings, "Booking", Icons.Default.List)
    )

    NavigationBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState().value
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(NavigationRoutes.Home) { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.label)
                },
                label = {
                    Text(text = item.label)
                }
            )
        }
    }
}