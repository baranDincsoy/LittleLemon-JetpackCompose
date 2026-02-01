package com.example.littlelemon

import CartViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel,
    onOpenMenu: () -> Unit
) {
    Column {
        TopAppBar(
            cartViewModel = cartViewModel,
            navController = navController,
            onOpenMenu = onOpenMenu
        )

        UpperPanel(
            cartViewModel = cartViewModel,
            navController = navController
        )

        LowerPanel(
            navController = navController,
            dishes = DishRepository.dishes
        )
    }
}