package com.example.littlelemon

import CartViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun UpperPanel(cartViewModel: CartViewModel, navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 16.dp)
            .background(LittleLemonColor.green)

    ) {
        Text(
            text = stringResource(id = R.string.title),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = LittleLemonColor.yellow,
            modifier = Modifier.padding(8.dp)

        )
        Text(
            text = stringResource(id = R.string.location),
            fontSize = 24.sp,
            color = LittleLemonColor.cloud,
            modifier = Modifier.padding(8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.description),
                style = MaterialTheme.typography.body1,
                color = LittleLemonColor.cloud,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(0.6f)
            )
            Image(
                painter = painterResource(id = R.drawable.upperpanelimage),
                contentDescription = "Upper Panel Image",
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .padding(8.dp)
            )
        }

        Button(
            onClick = {
                navController.navigate("Cart")
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.order_button_text))
        }
    }
}