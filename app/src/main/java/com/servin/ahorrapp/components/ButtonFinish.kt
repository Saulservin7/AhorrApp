package com.servin.ahorrapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.servin.ahorrapp.ui.theme.Green
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable

fun ButtonFinish(currentPage: Int,onClick:()->Unit) {

    Row(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = if (currentPage != 1) Arrangement.SpaceBetween else Arrangement.Center
    ) {

        if(currentPage==1)


        Button(
            onClick = onClick,
            modifier = Modifier.padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green)

        )

        {
            Text(text = "Continuar", color = Color.White)
        }

    }


}


