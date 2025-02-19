package com.servin.ahorrapp.view.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.servin.ahorrapp.view.bottombar.BottomNavigationBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Settings(navController: NavController, isDarkMode: Boolean, onThemeChange: (Boolean) -> Unit) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Configuración", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Modo oscuro")
                Spacer(modifier = Modifier.width(8.dp))
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { onThemeChange(it) } // Ahora notifica a MainActivity
                )
            }
        }
    }
}