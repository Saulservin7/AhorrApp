package com.servin.ahorrapp.view.onboarding

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.servin.ahorrapp.R
import com.servin.ahorrapp.components.ButtonFinish
import com.servin.ahorrapp.data.PageData
import com.servin.ahorrapp.viewmodel.OnBoardingViewModel

@Composable
fun OnBoardingPager(
    item: List<PageData>,
    pagerState: PagerState,
    modifier: Modifier,
    onBoardingViewModel: OnBoardingViewModel,
    navController: NavController
) {

    val onBoardingState = onBoardingViewModel.onboardingState.collectAsState()
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            HorizontalPager(pagerState) {
                Column(
                    modifier = Modifier
                        .padding(50.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LoaderData(
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth()
                            .align(alignment = Alignment.CenterHorizontally), item[it].image,
                        onBoardingViewModel
                    )

                    Text(
                        text = item[it].title,
                        modifier = Modifier.padding(top = 50.dp),
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = item[it].description,
                        color = Color.Black,
                        textAlign = TextAlign.Justify,
                        fontWeight = FontWeight.Light,
                        fontSize = 20.sp,

                    )
                    PageIndicator(item.size, pagerState.currentPage)
                }
            }

        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            ButtonFinish(pagerState.currentPage, onClick = {
                onBoardingViewModel.setOnBoarding(true)
                navController.navigate("home")
            })

        }


    }


}


