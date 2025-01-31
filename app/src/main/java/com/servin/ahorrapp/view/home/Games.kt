package com.servin.ahorrapp.view.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.servin.ahorrapp.data.Game
import com.servin.ahorrapp.data.GamesDescription
import com.servin.ahorrapp.viewmodel.RouletteViewModel

@Composable
fun Games(navController: NavController, viewModel: RouletteViewModel) {
    Column {
        GamesContent(viewModel)
    }
}

@Composable
fun GamesContent(viewModel: RouletteViewModel) {
    val rooms = viewModel.roomsList.collectAsState().value
    val gameImage =

        LazyColumn(
            modifier = Modifier
                .padding(top = 50.dp, start = 10.dp, end = 10.dp)
                .fillMaxHeight(),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            items(rooms.size) { index ->
                val room = rooms[index]
                Card(modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // ID de la sala
                        // Acceder a las propiedades de Game
                        when (val game = room.game) {
                            is Game.Ruleta -> {
                                Image(
                                    painter = painterResource(id = GamesDescription.Ruleta.image),
                                    contentDescription = "Ruleta",
                                    modifier = Modifier.size(150.dp)
                                )
                                Text(text = "Ruleta")
                                Text(text = "Rango: ${game.rangeStart} - ${game.rangeEnd}")
                            }

                            is Game.Trivia -> {
                                Text(text = " Trivia")
                                Text(text = "Preguntas: ${game.totalQuestions}")
                            }
                        }

                        Text(text = "Dinero Ahorrado: ${room.totalSaving}")
                    }
                }
            }
        }

}



