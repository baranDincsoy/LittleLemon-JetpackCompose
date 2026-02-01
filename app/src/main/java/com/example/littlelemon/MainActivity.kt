package com.example.littlelemon

import CartViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.littlelemon.ui.theme.LittleLemonTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val cartViewModel: CartViewModel = viewModel()

            LittleLemonTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)
                                .padding(top = 40.dp)
                        ) {
                            Text(
                                text = "Little Lemon",
                                style = MaterialTheme.typography.h4,
                                color = Color(0xFF495E57),
                                modifier = Modifier.padding(start = 24.dp, bottom = 32.dp)
                            )

                            TextButton(
                                onClick = {
                                    navController.navigate(Home.route)
                                    scope.launch { drawerState.close() }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp)
                            ) {
                                Text(
                                    text = "Home",
                                    style = MaterialTheme.typography.h6,
                                    color = Color(0xFF495E57),
                                    modifier = Modifier.fillMaxWidth().padding(start = 12.dp),
                                    textAlign = TextAlign.Start
                                )
                            }

                            val menuItems = listOf("Menu", "Reservation", "Cart", "Location", "About Us")
                            menuItems.forEach { item ->
                                TextButton(
                                    onClick = {
                                        scope.launch { drawerState.close() }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 12.dp)
                                ) {
                                    Text(
                                        text = item,
                                        style = MaterialTheme.typography.h6,
                                        color = Color.Black,
                                        modifier = Modifier.fillMaxWidth().padding(start = 12.dp),
                                        textAlign = TextAlign.Start
                                    )
                                }
                            }
                        }
                    }
                ) {
                    NavHost(navController = navController, startDestination = Home.route) {
                        composable(Home.route) {
                            HomeScreen(
                                navController = navController,
                                cartViewModel = cartViewModel,
                                onOpenMenu = {
                                    scope.launch { drawerState.open() }
                                }
                            )
                        }

                        composable(
                            DishDetails.route + "/{${DishDetails.argDishId}}",
                            arguments = listOf(navArgument(DishDetails.argDishId) { type = NavType.IntType })
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getInt(DishDetails.argDishId) ?: -1

                            DishDetails(
                                id = id,
                                cartViewModel = cartViewModel,
                                navController = navController,
                                onOpenMenu = {
                                    scope.launch { drawerState.open() }
                                }
                            )
                        }

                        composable(Cart.route) {
                            CartSummaryScreen(
                                cartViewModel = cartViewModel,
                                navController = navController,
                                onOpenMenu = {
                                    scope.launch { drawerState.open() }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}