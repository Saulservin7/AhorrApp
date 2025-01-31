package com.servin.ahorrapp.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.servin.ahorrapp.data.GamesDescription

@Composable
fun NewGame(navController: NavController) {
    val items = listOf(GamesDescription.Ruleta)
    Column(modifier = Modifier.fillMaxSize().padding(top = 50.dp)) {

        items.forEach() { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(15.dp)
                    .clickable {navController.navigate(item.name) }
            )
            {
                Text(
                    text = item.name,
                    modifier = Modifier.padding(10.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Image(
                    painter = painterResource(id = item.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )

                Text(
                    text = item.description,
                    modifier = Modifier.padding(15.dp),
                    fontSize = 15.sp
                )

            }

        }
    }

}