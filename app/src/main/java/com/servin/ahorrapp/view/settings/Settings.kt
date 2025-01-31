package com.servin.ahorrapp.view.settings

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.servin.ahorrapp.view.bottombar.BottomNavigationBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun Settings(navController: NavController) {
    Scaffold(bottomBar = { BottomNavigationBar(navController) }) {
        Text(text = "Settings")
    }


}



