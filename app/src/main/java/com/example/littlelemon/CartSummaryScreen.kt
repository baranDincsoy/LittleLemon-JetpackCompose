package com.example.littlelemon

import CartViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CartSummaryScreen(
    cartViewModel: CartViewModel,
    navController: NavHostController,
    onOpenMenu: () -> Unit
) {
    val items by cartViewModel.cartItems.collectAsState()
    val total by cartViewModel.totalPrice.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TopAppBar(
            cartViewModel = cartViewModel,
            navController = navController,
            onOpenMenu = onOpenMenu
        )

        Text(text = "Order Summary", style = MaterialTheme.typography.h4)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items) { dish ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = dish.name)
                    Text(text = "$${dish.price}")
                }
                Divider()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Total: $${String.format("%.2f", total)}", style = MaterialTheme.typography.h6)

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF4CE14))
        ) {
            Text("Checkout")
        }
    }
}