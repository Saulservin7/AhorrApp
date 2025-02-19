package com.servin.ahorrapp.view.dialog

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.servin.ahorrapp.data.Game
import com.servin.ahorrapp.model.Rooms
import com.servin.ahorrapp.navigation.NavigationItem
import com.servin.ahorrapp.viewmodel.RouletteViewModel

@Composable
fun NewGameDialog(
    navController: NavController,
    rouletteViewModel: RouletteViewModel,
    onDismiss: () -> Unit
) {
    val newRoomId by rouletteViewModel.newRoomId.collectAsState()

    LaunchedEffect(newRoomId) {
        newRoomId?.let { id ->
            val route = "${NavigationItem.Ruleta.route}?id=$id"
            Log.d("ROOMID", "id enviado : $id")
            navController.navigate(route)
            rouletteViewModel.clearNewRoomId()
            onDismiss() // Cierra el diálogo después de navegar
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = "Establece un rango mínimo y máximo para obtener un número girando la ruleta.",
            fontSize = 17.sp,
            lineHeight = 20.sp
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(100.dp),
                    value = rouletteViewModel.initvalue.value,
                    onValueChange = { rouletteViewModel.setInitValue(it) },
                    label = { Text("Mín") }
                )

                Text(
                    text = "a",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                TextField(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(100.dp),
                    value = rouletteViewModel.finalvalue.value,
                    onValueChange = { rouletteViewModel.setFinalValue(it) },
                    label = { Text("Máx") }
                )
            }

            Button(
                onClick = {

                    val initValue = rouletteViewModel.initvalue.value.toInt()
                    val finalValue = rouletteViewModel.finalvalue.value.toInt()
                    val ruletaGame =
                        Game.Ruleta(rangeStart = initValue, rangeEnd = finalValue, type = "ruleta", usedNumbers = "")
                    rouletteViewModel.addRoom(
                        Rooms(
                            game = ruletaGame,
                            userId = 1, // Reemplaza con el ID de usuario correspondiente
                            totalSaving = 0L
                        )
                    )


                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 20.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Crear juego",
                    fontSize = 16.sp
                )
            }
        }
    }
}



