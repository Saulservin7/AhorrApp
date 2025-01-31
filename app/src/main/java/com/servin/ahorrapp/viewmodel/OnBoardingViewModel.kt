package com.servin.ahorrapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.servin.ahorrapp.R
import com.servin.ahorrapp.data.PageData
import com.servin.ahorrapp.datastore.StoreBoarding
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val storeBoarding: StoreBoarding) :
    ViewModel() {


    private val _isReady = MutableStateFlow(false)
    val isReady: StateFlow<Boolean> get() = _isReady


    private val _items = ArrayList<PageData>()
    val items = _items


    init {
        LoadItems()

    }


    private fun LoadItems() {
        _items.add(
            PageData(
                "Bienvenido",
                "AhorrApp es una aplicación que le permitira ahorrar dinero mediante dinamicas.",
                R.raw.welcome
            )
        )
        _items.add(
            PageData(
                "¿Cómo funciona?",
                "Selecciona la dinamica con la que quieres ahorrar y sigue las instrucciones.",
                R.raw.save
            )
        )

    }

    val onboardingState = storeBoarding.getOnboarding
        .stateIn(viewModelScope, SharingStarted.Lazily, true)


    fun setOnBoarding(value: Boolean) {
        viewModelScope.launch {
            storeBoarding.setOnboarding(value)
        }
    }


}