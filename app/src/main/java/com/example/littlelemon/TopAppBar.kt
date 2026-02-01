package com.example.littlelemon

import CartViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun TopAppBar(
    cartViewModel: CartViewModel,
    navController: NavHostController,
    onOpenMenu: () -> Unit
) {
    val cartCount by cartViewModel.cartCount.collectAsState()

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onOpenMenu() }) {
            Image(
                painter = painterResource(id = R.drawable.ic_hamburger_menu),
                contentDescription = "Menu Icon",
                modifier = Modifier.size(24.dp)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.littlelemonimgtxt_nobg),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier.fillMaxWidth(0.5F).padding(horizontal = 20.dp)
        )

        IconButton(onClick = { navController.navigate("Cart") }) {
            BadgedBox( badge = {
                if (cartCount > 0) {
                    Badge(
                        backgroundColor = Color(0xFFF4CE14),
                        contentColor = Color.Black
                    ) {
                        Text(text = cartCount.toString())
                    }
                }
            }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_cart),
                    contentDescription = "Cart",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    TopAppBar(
        cartViewModel = CartViewModel(),
        navController = rememberNavController(),
        onOpenMenu = { }
    )
}