package com.servin.ahorrapp.view.games

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.servin.ahorrapp.R
import com.servin.ahorrapp.data.Game
import com.servin.ahorrapp.model.Rooms
import com.servin.ahorrapp.ui.theme.Green
import com.servin.ahorrapp.viewmodel.RouletteViewModel


@Composable
fun Roulette(navController: NavController, rouletteViewModel: RouletteViewModel) {


    RouletteContent(rouletteViewModel)


}

@Composable
fun RouletteContent(rouletteViewModel: RouletteViewModel) {
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
        Text(
            "Establece un rango para obtener números de la ruleta ",
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )

        if (rouletteViewModel.showFieldsState.value) {

            Row {
                OutlinedTextField(

                    value = rouletteViewModel.initvalue.value,
                    onValueChange = { rouletteViewModel.setInitValue(it) },
                    modifier = Modifier
                        .padding(10.dp)
                        .height(50.dp)
                        .width(80.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Text("a", modifier = Modifier.padding(top = 20.dp))

                OutlinedTextField(
                    value = rouletteViewModel.finalvalue.value,
                    onValueChange = { rouletteViewModel.setFinalValue(it) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .padding(10.dp)
                        .height(50.dp)
                        .width(80.dp)
                )

                OutlinedButton(
                    onClick = {
                        val initValue = rouletteViewModel.initvalue.value.toInt()
                        val finalValue = rouletteViewModel.finalvalue.value.toInt()
                        val ruletaGame = Game.Ruleta(rangeStart = initValue, rangeEnd = finalValue)
                        rouletteViewModel.addRoom(
                            Rooms(
                                game = ruletaGame,
                                userId = 1, // Reemplaza con el ID de usuario correspondiente
                                totalSaving = 0L
                            )
                        )
                        rouletteViewModel.showFields()


                    },
                    enabled = rouletteViewModel.initvalue.value.isNotEmpty() && rouletteViewModel.finalvalue.value.isNotEmpty()
                ) {
                    Text(text = "Guardar")
                }
            }


        }
        Image(
            painter = painterResource(id = R.drawable.ruleta),
            contentDescription = null,
            modifier = Modifier
                .size(600.dp)
                .rotate(animatedRotation)

        )
        if (!rouletteViewModel.showFieldsState.value) {
            Button(
                modifier = Modifier.padding(30.dp),
                colors = ButtonDefaults.buttonColors(Green),
                onClick = { rouletteViewModel.startRotation() },
            ) {
                Text("Girar")

            }

        }


    }
}




