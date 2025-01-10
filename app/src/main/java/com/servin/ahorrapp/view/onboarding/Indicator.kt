package com.servin.ahorrapp.view.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.servin.ahorrapp.ui.theme.Green


@Composable
fun PageIndicator(size: Int, currentPage: Int) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 50.dp)
    ) {

        repeat(size){
            Indicator(isSelected = it == currentPage)
        }

    }


}

@Composable
fun Indicator(isSelected: Boolean) {

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .width(10.dp)
            .height(5.dp)
            .background(
                if (isSelected) Green else Color.Gray
            )
    ) {


    }

}


@Preview(showBackground = true)
@Composable
fun IndicatorPreview() {
   PageIndicator(2, 1)
}