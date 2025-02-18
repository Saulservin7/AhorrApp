package com.servin.ahorrapp.view.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.servin.ahorrapp.data.GamesDescription
import com.servin.ahorrapp.view.dialog.NewGameDialog
import com.servin.ahorrapp.viewmodel.RouletteViewModel

@Composable
fun NewGame(navController: NavController,rouletteViewModel: RouletteViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    val items = listOf(GamesDescription.Ruleta, GamesDescription.Trivia)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
    ) {

        items.forEach() { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(15.dp)
                    .clickable { showDialog = true },
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
    if (showDialog) NewGameDialog(navController,showDialog,rouletteViewModel,onDismiss = { showDialog = false })

}


@Composable
fun NewGameDialog(navController: NavController,showDialog: Boolean,rouletteViewModel: RouletteViewModel,onDismiss: () -> Unit) {

    Dialog(
        onDismissRequest = onDismiss, properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )

    ) {
        AnimatedVisibility(
            visible = true,
            enter = slideInVertically()+ fadeIn(),
            exit = slideOutVertically()+ fadeOut()
        ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f) // 75% del alto
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            NewGameDialog(navController,rouletteViewModel,onDismiss)
        }
    }
}

}