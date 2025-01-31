package com.servin.ahorrapp.view.bottombar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.servin.ahorrapp.navigation.NavigationItem

@Composable

fun BottomNavigationBar(navController: NavController) {
    val items = listOf(NavigationItem.Home, NavigationItem.Profile, NavigationItem.Settings)


    NavigationBar {
        items.forEach {
                 item ->
            NavigationBarItem(
                icon = { IconNavigation(item.icon)},
                label = { Text(item.title) },
                selected = false,
                onClick = { navController.navigate(item.route) }
            )
        }
    }


}

@Composable
fun IconNavigation(item:Int?=null,){
    
    Box() {
        item?.let { painterResource(id = it) }?.let {
            Icon(
                painter = it,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }

    }

}





