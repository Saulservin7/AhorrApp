package com.servin.ahorrapp.view.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.servin.ahorrapp.R
import com.servin.ahorrapp.components.ButtonFinish
import com.servin.ahorrapp.data.PageData
import com.servin.ahorrapp.viewmodel.OnBoardingViewModel

@Composable
fun OnBoardingPager(
    item: List<PageData>,
    pagerState: PagerState,
    modifier: Modifier,
    onBoardingViewModel: OnBoardingViewModel
) {
    Box(modifier) {
        Column {
            HorizontalPager(pagerState) {
                LoaderData(
                    modifier = Modifier
                        .size(200.dp)
                        .fillMaxWidth()
                        .align(alignment = Alignment.CenterHorizontally),
                    item[it].image,
                    onBoardingViewModel
                )
                Text(text = item[it].title,
                    modifier=Modifier.padding(top = 50.dp), color = Color.Black
                )
                Text(text = item[it].description,
                     color = Color.Black
                )
            }
            PageIndicator(item.size, pagerState.currentPage)
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            ButtonFinish(pagerState.currentPage)

        }


    }


}


