package com.servin.ahorrapp.view.onboarding

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.servin.ahorrapp.viewmodel.OnBoardingViewModel


@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainOnBoarding(onBoardingViewModel: OnBoardingViewModel) {
    val size = onBoardingViewModel.items.size

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { size })

    OnBoardingPager(
        item = onBoardingViewModel.items,
        pagerState = pagerState,
        modifier = Modifier.fillMaxSize(),
        onBoardingViewModel = onBoardingViewModel
    )


}