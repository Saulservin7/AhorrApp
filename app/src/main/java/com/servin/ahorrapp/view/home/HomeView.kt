package com.servin.ahorrapp.view.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.servin.ahorrapp.view.bottombar.BottomNavigationBar
import com.servin.ahorrapp.viewmodel.RouletteViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun HomeView(navController: NavController,viewModel: RouletteViewModel) {
    val tabs = listOf("Juegos Actuales", "Juego Nuevo")
    val selectedTabIndex = remember { mutableStateOf(0) }

    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TabRow(selectedTabIndex = selectedTabIndex.value) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex.value == index,
                        onClick = {
                            coroutineScope.launch {
                                selectedTabIndex.value = index
                            }
                        },
                        text = { Text(title) }
                    )
                }
            }
        },
        bottomBar = { BottomNavigationBar(navController) },
        modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars.only(WindowInsetsSides.Top))
    ) {

        when (selectedTabIndex.value) {
            0 -> Games(navController,viewModel)
            1 -> NewGame(navController)
        }

    }
}







