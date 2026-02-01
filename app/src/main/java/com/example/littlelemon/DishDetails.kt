package com.example.littlelemon

import CartViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlin.math.max

@Composable
fun DishDetails(
    id: Int,
    cartViewModel: CartViewModel = CartViewModel(),
    navController: NavHostController,
    onOpenMenu: () -> Unit
) {
    val dish = DishRepository.getDish(id) ?: return

    var quantity by remember { mutableStateOf(1) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            cartViewModel = cartViewModel,
            navController = navController,
            onOpenMenu = onOpenMenu
        )

        Image(
            painter = painterResource(id = dish.imageResource),
            contentDescription = "Dish Image",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Text(
                text = dish.name,
                style = MaterialTheme.typography.h1
            )

            Text(
                text = dish.description,
                style = MaterialTheme.typography.body1
            )

            Counter(
                value = quantity,
                onValueChange = { quantity = it }
            )

            Button(
                onClick = {
                    repeat(quantity) {
                        cartViewModel.addToCart(dish)
                    }
                    navController.navigate("Cart")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = androidx.compose.material.ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE4CD05))
            ) {
                val totalPrice = dish.price * quantity
                Text(
                    text = stringResource(id = R.string.add_for) + " $${totalPrice}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun Counter(
    value: Int,
    onValueChange: (Int) -> Unit,
    minValue: Int = 1,
    step: Int = 1,
    buttonSize: Dp = 48.dp,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        TextButton(
            onClick = { onValueChange(max(minValue, value - step)) },
            modifier = Modifier.size(buttonSize)
        ) {
            Text(text = "-", style = MaterialTheme.typography.h2, color = Color.Black)
        }
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(16.dp)
        )
        TextButton(
            onClick = { onValueChange(value + step) },
            modifier = Modifier.size(buttonSize)
        ) {
            Text(text = "+", style = MaterialTheme.typography.h2, color = Color.Black)
        }
    }
}