package com.servin.ahorrapp.view.games.roulette

import android.util.Log
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.servin.ahorrapp.R
import com.servin.ahorrapp.data.Game
import com.servin.ahorrapp.ui.theme.Green
import com.servin.ahorrapp.viewmodel.RouletteViewModel


@Composable
fun Roulette(
    navController: NavController,
    rouletteViewModel: RouletteViewModel,
    roomId: Int? = null
) {


    RouletteContent(rouletteViewModel, roomId)
}

@Composable
fun RouletteContent(rouletteViewModel: RouletteViewModel, roomId: Int?) {
    var showNumberGrid by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        rouletteViewModel.getRoomById(roomId ?: 0)
    }

    DisposableEffect(Unit) {
        Log.d("Lifecycle", "Composing Roulette for room $roomId")
        onDispose {
            Log.d("Lifecycle", "Disposing Roulette for room $roomId")
            rouletteViewModel.clearNewRoomId() // Añade este método en tu ViewModel
        }
    }
    val roomData by rouletteViewModel.room.collectAsState()
    val rangeStart = (roomData?.game as? Game.Ruleta)?.rangeStart
    val rangeEnd = (roomData?.game as? Game.Ruleta)?.rangeEnd
    val rotationAngle by rouletteViewModel.rotationAngle.collectAsState()
    // Animación de rotación suave
    val animatedRotation by animateFloatAsState(
        targetValue = rotationAngle,
        animationSpec = tween(durationMillis = 3000, easing = LinearOutSlowInEasing)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { showNumberGrid = true },
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.DarkGray
            ),
            enabled = rangeStart != null && rangeEnd != null
        ) {
            Text("Ver números")
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {


            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)  // Altura fija para igualar tamaño
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {


                    Text(
                        text = "Rango",
                        fontSize = 18.sp
                    )
                    Text(
                        text = "$rangeStart - $rangeEnd",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }
            }

            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)  // Altura fija para igualar tamaño
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Text(
                        text = "Dinero Acumulado",
                        fontSize = 18.sp
                    )
                    Text(
                        text = "$${roomData?.totalSaving}",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }


            }

        }

        Box(
            modifier = Modifier
                .size(500.dp)
                .padding(20.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = "Gira la ruleta para saber cuanto dinero ahorrar",
                modifier = Modifier.padding(20.dp)
            )


            Image(
                painter = painterResource(id = R.drawable.ruleta),
                contentDescription = null,
                modifier = Modifier
                    .size(600.dp)
                    .rotate(animatedRotation)

            )

        }



        Button(
            modifier = Modifier.padding(30.dp),
            colors = ButtonDefaults.buttonColors(Green),
            onClick = {
                rouletteViewModel.startRotation()

                rouletteViewModel.fetchRandomNumber(
                    id = roomId ?: 0,
                    totalSaving = roomData?.totalSaving ?: 0,
                    min = rangeStart ?: 0,
                    max = rangeEnd ?: 0,
                    usedNumbers = (roomData?.usedNumbers ?: "")

                )

                Log.d("Roulette", "total: ${roomData?.totalSaving}")



            },
        ) {
            Text("Girar")

        }
        Text(
            text = "Ultimos Números : ${
                roomData?.usedNumbers?.split(",")
                    ?.reversed()
                    ?.joinToString(",")?.take(11)
            }",
            modifier = Modifier.padding(20.dp)
        )
        if (showNumberGrid && rangeStart != null && rangeEnd != null) {
            NumberGridDialog(
                initial = rangeStart,
                final = rangeEnd,
                onDismiss = { showNumberGrid = false },
                usedNumbers = (roomData?.usedNumbers ?: "")
            )
        }

    }


}

